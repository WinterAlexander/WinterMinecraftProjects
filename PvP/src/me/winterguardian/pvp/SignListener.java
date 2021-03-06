package me.winterguardian.pvp;

import me.winterguardian.core.util.TextUtil;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * Created by Alexander Winter on 2015-12-11.
 */
public class SignListener implements Listener
{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getClickedBlock() == null || event .getClickedBlock().getState() == null)
			return;

		if(!(event.getClickedBlock().getState() instanceof Sign))
			return;

		Sign sign = (Sign)event.getClickedBlock().getState();

		if(!sign.getLines()[0].contains("§4§lPvP"))
			return;

		if(sign.getLines()[1].equalsIgnoreCase("§e§lVote"))
		{
			if(!sign.getLines()[0].equals("§4§lTouchedPvP"))
			{
				sign.setLine(0, "§4§lTouchedPvP");
				sign.update();
			}
			event.getPlayer().performCommand("pvp vote " + TextUtil.removeColorCodes(sign.getLine(2), '§'));
			return;
		}

		if(sign.getLines()[1].equalsIgnoreCase("§6§lTop"))
		{
			if(!sign.getLines()[0].equals("§4§lTouchedPvP"))
			{
				sign.setLine(0, "§4§lTouchedPvP");
				sign.update();
			}
			event.getPlayer().performCommand("pvp top " + TextUtil.removeColorCodes(sign.getLine(2), '§'));
			return;
		}

		if(sign.getLines()[1].equalsIgnoreCase("§a§lStats"))
		{
			if(!sign.getLines()[0].equals("§4§lTouchedPvP"))
			{
				sign.setLine(0, "§4§lTouchedPvP");
				sign.update();
			}
			event.getPlayer().performCommand("pvp stats " + TextUtil.removeColorCodes(sign.getLine(2), '§'));
			return;
		}
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event)
	{
		if(!event.getPlayer().hasPermission(PvPPlugin.STAFF))
			return;

		if(!event.getLines()[0].equalsIgnoreCase("[pvp]"))
			return;

		event.setLine(0, "§4§lTouchedPvP");

		if(event.getLines()[1].equalsIgnoreCase("vote"))
		{
			event.setLine(1, "§e§lVote");
			event.setLine(2, "§f" + event.getLine(2));
			return;
		}

		if(event.getLines()[1].equalsIgnoreCase("top"))
		{
			event.setLine(1, "§6§lTop");
			event.setLine(2, "§f" + event.getLine(2));
			return;
		}

		if(event.getLines()[1].equalsIgnoreCase("stats"))
		{
			event.setLine(1, "§a§lStats");
			event.setLine(2, "§f" + event.getLine(2));
			return;
		}
	}
}
