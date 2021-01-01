package com.ninjagold;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gold")
public class GoldController {
	@RequestMapping("")
	public String dislayGold(HttpSession session) {
		Integer gold = (Integer) session.getAttribute("gold");
		if (gold == null) { gold = 0;}
		session.setAttribute("gold", gold);
		
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("activities");
		if (activities == null) { activities = new ArrayList<String>();}
		session.setAttribute("activities", activities);
		ArrayList<String> textColors = (ArrayList<String>) session.getAttribute("textColors");
		if (textColors == null) { textColors = new ArrayList<String>();}
		session.setAttribute("textColors", textColors);
		
		return "gold.jsp";
	}
	
	@RequestMapping(value="/{request}", method=RequestMethod.POST)
	public String processClick(HttpSession session, @PathVariable("request") String request) {
		
		Integer gold = (Integer) session.getAttribute("gold");
		Integer earnedGold = 0;
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("activities");
		if (request.equals("reset")) {
			earnedGold  = -gold;
			activities = null;
			session.setAttribute("activities", activities);
		}
		else {
			Random r = new Random();
			String activity = new String();
			ArrayList<String> timestamps = (ArrayList<String>) session.getAttribute("timestamps");
			Date curDate = new Date();
//			String dateTime = String.format("%tB %<td, %<tY ", curDate) + String.format("%tI:%<tM %<Tp", curDate);
			String dateTime = String.format("%tB %<td, %<tY %<tI:%<tM %<Tp", curDate);
//			String dateTime = "test datetime";
			if (request.equals("farm")) {
				earnedGold = r.nextInt(21-10) + 10; // int number = random.nextInt(max - min) + min; to get a random interger from 10 to 20.
			}
			if (request.equals("cave")) {
				earnedGold = r.nextInt(11-5) + 5; // int number = random.nextInt(max - min) + min; to get a random interger from 5 to 10.
			}
			if (request.equals("house")) {
				earnedGold = r.nextInt(6-2) + 2; // int number = random.nextInt(max - min) + min; to get a random interger from 2 to 5.
			}
			if (request.equals("casino")) {
				earnedGold = r.nextInt(51-(-50)) -50; // int number = random.nextInt(max - min) + min; to get a random interger from -50 to 50.
			}
			
			if (earnedGold > 0) {
				activity = "You entered a "+ request +" and earned "+ earnedGold + " gold. (" + dateTime + ")";
			}
			else if (earnedGold < 0) {
				activity = "You entered a casino and lost "+ earnedGold + " gold. Ouch!! (" + dateTime + ")";
			}
			else {
				activity = "You entered a casino and earned 0 gold. (" + dateTime + ")";
			}
			activities.add(activity);
			session.setAttribute("activities", activities);
			
			
		} // end of else (to set action for when buttons other than reset)
		System.out.println("earnedGold: "+ earnedGold + "; activities:" + activities);
		
		gold += earnedGold;
		session.setAttribute("gold", gold);
		
		return "redirect:/gold";
	}
	
}
//Send the ninja to a debtors prison on a second rendered page if the ninja falls too far into debt (15-60 minutes)
//before the return on row 83: do an if condition for gold value. Like if gold < -100 then render prison.jsp. Else render redirect.
//Decided not to do this at this point since I want to move forward to get to the end of this course.

