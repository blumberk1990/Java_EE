package pl.mgd.wykopek.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.mgd.wykopek.model.Discovery;
import pl.mgd.wykopek.model.User;
import pl.mgd.wykopek.model.Vote;
import pl.mgd.wykopek.model.VoteType;
import pl.mgd.wykopek.service.DiscoveryService;
import pl.mgd.wykopek.service.VoteService;

/**
 * Servlet implementation class VoteController
 * example URL /vote?discovery_id=3&vote=VOTE_UP
 */
@WebServlet("/vote")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(loggedUser != null) {
			VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
			long userId = loggedUser.getId();
			long discoveryId = Long.parseLong(request.getParameter("discovery_id"));
			updateVote(userId, discoveryId, voteType);
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

	private void updateVote(long userId, long discoveryId, VoteType voteType) {
		VoteService voteService = new VoteService();
		Vote existingVote = voteService.getVoteByDiscoveryUserId(discoveryId, userId);
		Vote updateVote = voteService.addOrUpdateVote(discoveryId, userId, voteType);
		if(existingVote != updateVote || !updateVote.equals(existingVote)) {
			updateDiscovery(discoveryId, existingVote, updateVote);
		}
	}
	
	private void updateDiscovery(long discoveryId, Vote oldVote, Vote newVote) {
		DiscoveryService discoveryService = new DiscoveryService();
		Discovery discoveryById = discoveryService.getDiscoveryById(discoveryId);
		Discovery updatedDiscover = null;
		if(oldVote == null && newVote != null) {
			updatedDiscover = addDiscoveryVote(discoveryById, newVote.getVoteType());
		} else if(oldVote != null && newVote != null) {
			updatedDiscover = removeDiscoveryVote(discoveryById, oldVote.getVoteType()); 
			updatedDiscover = addDiscoveryVote(updatedDiscover, newVote.getVoteType()); 
		}
		discoveryService.updateDiscovery(updatedDiscover);
	}
	
	private Discovery addDiscoveryVote(Discovery discovery, VoteType voteType) {
		Discovery discoveryCopy = new Discovery(discovery); 
		if(voteType == VoteType.VOTE_UP) {
			discoveryCopy.setUpVote(discoveryCopy.getUpVote() + 1);
		} else if(voteType == VoteType.VOTE_DOWN) {
			discoveryCopy.setDownVote(discoveryCopy.getDownVote() + 1);
		}
		return discoveryCopy;
	}
	
	private Discovery removeDiscoveryVote(Discovery discovery, VoteType voteType) {
		Discovery discoveryCopy = new Discovery(discovery); 
		if(voteType == VoteType.VOTE_UP && discoveryCopy.getUpVote() != 0) {
			discoveryCopy.setUpVote(discoveryCopy.getUpVote() - 1);
		} else if(voteType == VoteType.VOTE_DOWN && discoveryCopy.getDownVote() != 0) {
			discoveryCopy.setDownVote(discoveryCopy.getDownVote() - 1);
		}
		return discoveryCopy;
	}
	
}
