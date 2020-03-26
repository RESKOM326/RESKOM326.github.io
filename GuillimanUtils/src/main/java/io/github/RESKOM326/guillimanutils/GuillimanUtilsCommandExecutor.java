package io.github.RESKOM326.guillimanutils;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuillimanUtilsCommandExecutor implements CommandExecutor {
	private final GuillimanUtils plugin;
	
	public GuillimanUtilsCommandExecutor(GuillimanUtils plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		if(command.getName().equalsIgnoreCase("ignite")) {
			try {
				if(args.length != 2) {		// Usage: /ignite <player> <time(s)>
					return false;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					sender.sendMessage("Player "+ args[0] + " is offline or does not exist");
					return true;
				}
				target.setFireTicks(20 * Integer.parseInt(args[1]));
				Bukkit.broadcastMessage(args[0] + " is being purified");
				return true;				
			} catch(IllegalArgumentException e) {
				System.err.print("Bad username");
			}
		}
		else if(command.getName().equalsIgnoreCase("hideme")) {		// Only players can cast this command
			// Usage: /hideme [playerList]
			try {
				if(!(sender instanceof Player)) {
					sender.sendMessage("This command must be issued by a player");
					return true;
				}
				Player p = (Player) sender;
				if(args.length == 0) {		// Hides the caster from every player
					for(Player targets : Bukkit.getOnlinePlayers()) {
						if(!targets.canSee(p)) {
							continue;
						}
						targets.hidePlayer(plugin, p);
					}
					return true;
				}
				else if(args.length > 0) {		// Hides the caster from the chosen players
					for(int i = 0; i < args.length; i++) {
						Player target = Bukkit.getServer().getPlayer(args[i]);;
						if(target == null) {
							sender.sendMessage("Player " + args[i] + " is offline or does not exist");
							continue;
						}
						if(!target.canSee(p)) {
							continue;
						}
						target.hidePlayer(plugin, p);
					}
					return true;
				}				
			} catch(IllegalArgumentException e) {
				System.err.print("Bad username");
			}

		}
		else if(command.getName().equalsIgnoreCase("unhideme")) {		// Only players can cast this command
			// Usage: /unhideme [playerList]
			try {
				if(!(sender instanceof Player)) {
					sender.sendMessage("This command must be issued by a player");
					return true;
				}
				Player p = (Player) sender;
				if(args.length == 0) {		// Unhides the caster from every player
					for(Player targets : Bukkit.getOnlinePlayers()) {
						if(targets.canSee(p)) {
							continue;
						}
						targets.showPlayer(plugin, p);
					}
					return true;
				}
				else if(args.length > 0) {		// Unhides the caster from the chosen players
					for(int i = 0; i < args.length; i++) {
						Player target = Bukkit.getServer().getPlayer(args[i]);
						if(target == null) {
							sender.sendMessage("Player " + args[i] + " is offline or does not exist");
							continue;
						}
						if(target.canSee(p)) {
							continue;
						}
						target.showPlayer(plugin, p);
					}
					return true;
				}
			} catch(IllegalArgumentException e) {
				System.err.print("Bad username");
			}
		}
		return false;
	}

}
