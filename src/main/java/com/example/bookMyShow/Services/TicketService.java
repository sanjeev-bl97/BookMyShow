package com.example.bookMyShow.Services;

import com.example.bookMyShow.Dtos.RequestDto.TicketRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.TicketResponseDto;
import com.example.bookMyShow.Exception.ShowNotFound;
import com.example.bookMyShow.Exception.UserNotFound;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.ShowSeat;
import com.example.bookMyShow.Models.Ticket;
import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.Repository.ShowRepository;
import com.example.bookMyShow.Repository.TicketRepository;
import com.example.bookMyShow.Repository.UserRepository;
import com.example.bookMyShow.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    JavaMailSender javaMailSender;

    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws Exception {

        Optional<User> userOptional = userRepository.findById(ticketRequestDto.getUserId());

        if(userOptional.isEmpty())
            throw new UserNotFound("User with ID : "+ticketRequestDto.getUserId() +"not found");

        Optional<Show> showOptional = showRepository.findById(ticketRequestDto.getShowId());

        if(showOptional.isEmpty())
            throw new ShowNotFound("Show with ID : "+ticketRequestDto.getShowId() +"not found");

        User user = userOptional.get();
        Show show = showOptional.get();
        List<String> reqSeats = ticketRequestDto.getReqSeatList();

        if(!isValidShow(show,reqSeats))
            throw new Exception("Required seats not available");

        int totalCost = calculateCost(show,reqSeats);
        String bookedTickets = reqSeats.stream().reduce("",(a,b) -> a+=b+",");



        Ticket ticket = new Ticket();

        ticket.setTotalPrice(totalCost);
        ticket.setBookedSeats(bookedTickets);
        ticket.setShow(show);
        ticket.setUser(user);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);

        userRepository.save(user);
        showRepository.save(show);

        //We can send a mail to the person
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" ! \n"+
                "You have successfully booked a ticket. Please find the following details \n"
                +"booked seat No's: "  + bookedTickets+"\n"
                +"movie Name: " + show.getMovie().getName()+"\n"
                +"show Date is:  "+show.getShowDate()+"\n"
                +"And show time is: "+show.getShowTime()+"\n"
                +"Enjoy the Show !!!";

        simpleMessageMail.setSubject("Ticket Confirmation Mail");
        simpleMessageMail.setFrom("pwspbl@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmailId());

        javaMailSender.send(simpleMessageMail);


        return TicketTransformer.createTicketResponseDto(show,ticket);


    }

    public boolean isValidShow(Show show,List<String> reqSeats){
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat: showSeatList){
            if(reqSeats.contains(showSeat.getSeatNo()))
                if(!showSeat.isAvailable())
                    return false;
        }

        return true;
    }

    public int calculateCost(Show show,List<String> reqSeats){
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int cost = 0;

        for(ShowSeat showSeat: showSeatList) {
            if (reqSeats.contains(showSeat.getSeatNo())) {
                cost += showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return cost;
    }

    public void cancelTicket(Integer id) {

        User user = ticketRepository.findById(id).get().getUser();
        Show show = ticketRepository.findById(id).get().getShow();
        ticketRepository.deleteById(id);

        //We can send a mail to the person
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" ! \n"+
                "You have successfully cancelled the ticket.\n"
                +"Movie Name: " + show.getMovie().getName()+"\n"
                +"show Date is:  "+show.getShowDate()+"\n"
                +"And show time is: "+show.getShowTime();

        simpleMessageMail.setSubject("Ticket Cancellation");
        simpleMessageMail.setFrom("pwspbl@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmailId());

        javaMailSender.send(simpleMessageMail);
    }

}
