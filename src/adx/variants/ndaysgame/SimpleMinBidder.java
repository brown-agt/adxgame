package adx.variants.ndaysgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import adx.agent.AgentLogic;
import adx.agent.OnlineAgent;
import adx.exceptions.AdXException;
import adx.server.OfflineGameServer;
import adx.structures.Campaign;
import adx.structures.SimpleBidEntry;
import adx.util.AgentStartupUtil;
import adx.util.Logging;

public class SimpleMinBidder extends NDaysNCampaignsAgent {

	/**
	 * Constructor.
	 * 
	 * @param host
	 * @param port
	 */
	public SimpleMinBidder() {
		super();
	}

	@Override
	protected Set<NDaysAdBidBundle> getAdBids() throws AdXException {
		// Log for which day we want to compute the bid bundle.
		Set<NDaysAdBidBundle> set = new HashSet<>();

		for (Campaign c : this.getActiveCampaigns()) {
			double budgetLeft = c.getBudget() - this.getCumulativeCost(c);
			double reachLeft = c.getReach() - this.getCumulativeReach(c);
			double bidf = c.getBudget()/c.getReach();
			if (reachLeft <= 0) {
				bidf = 0;
			}
			Set<SimpleBidEntry> bids = new HashSet<>();
			SimpleBidEntry bid = new SimpleBidEntry(c.getMarketSegment(), bidf , budgetLeft);
			bids.add(bid);
			NDaysAdBidBundle bundle = new NDaysAdBidBundle(c.getId(), c.getBudget() - this.getCumulativeCost(c), bids);
			set.add(bundle);
		}
		
		return set;
	}

	@Override
	protected Map<Campaign, Double> getCampaignBids(Set<Campaign> campaignsForAuction) {
		Map<Campaign, Double> campaigns = new HashMap<>();
		for (Campaign c : campaignsForAuction) {
			campaigns.put(c, c.getReach() * 0.1);
		}
		return campaigns;
	}

	@Override
	protected void onNewGame() {
		// TODO Auto-generated method stub
	}

}
