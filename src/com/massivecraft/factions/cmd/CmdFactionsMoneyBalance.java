package com.massivecraft.factions.cmd;

import com.massivecraft.factions.integration.Econ;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdFactionsMoneyBalance extends FCommand
{
	public CmdFactionsMoneyBalance()
	{
		super();
		
		this.addAliases("b", "balance");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "your");
		
		this.addRequirements(ReqHasPerm.get(Perm.MONEY_BALANCE.node));
		
		this.setHelpShort("show faction balance");
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		Faction faction = myFaction;
		if (this.argIsSet(0))
		{
			faction = this.argAsFaction(0);
		}
			
		if (faction == null) return;
		if (faction != myFaction && ! Perm.MONEY_BALANCE_ANY.has(sender, true)) return;
		
		Econ.sendBalanceInfo(fme, faction);
	}
	
}