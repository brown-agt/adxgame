package adx.offlinesim;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import adx.agent.AgentLogic;
import adx.agent.OfflineAgent;
import adx.exceptions.AdXException;
import adx.server.OfflineGameServer;
import adx.server.OfflineGameServerAbstract;
import adx.variants.ndaysgame.Tier1NDaysNCampaignsAgent;
import adx.variants.ndaysgame.SimpleMinBidder;
import adx.variants.ndaysgame.SimpleNDayAgent;

public class OfflineSim {
	private OfflineGameServerAbstract server;
	private Collection<AgentLogic> agents;
	
	public OfflineSim(OfflineGameServerAbstract server, Collection<AgentLogic> agents) {
		this.server = server;
		this.agents = agents;
	}
	
	public void run() throws AdXException {
		int i = 1;
		for (AgentLogic logic : this.agents) {
			OfflineAgent agent = new OfflineAgent(this.server, logic);
			agent.connect("Agent" + i, "123456");
			i++;
		}
		this.server.runAdXGame();
	}
	
	public static void main(String[] args) throws IOException, AdXException {
		OfflineGameServer.initParams(args);
		OfflineGameServerAbstract server = new OfflineGameServer();
		Collection<AgentLogic> agents = Arrays.asList(
			new SimpleMinBidder(0.),
			new SimpleMinBidder(0.),
			new SimpleMinBidder(0.),
			new SimpleNDayAgent(),
			new SimpleNDayAgent(),
			new SimpleNDayAgent(),
			new SimpleNDayAgent(),
			new SimpleNDayAgent(),
			new SimpleNDayAgent(),
			new SimpleNDayAgent());;
		new OfflineSim(server, agents).run();
	}
}
