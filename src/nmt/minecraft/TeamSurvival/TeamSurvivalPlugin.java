package nmt.minecraft.TeamSurvival;

import org.bukkit.plugin.java.JavaPlugin;

import nmt.minecraft.TeamSurvival.IO.CreationCommand;
import nmt.minecraft.TeamSurvival.IO.CreationTabCompleter;
import nmt.minecraft.TeamSurvival.IO.JoinTeamCommand;
import nmt.minecraft.TeamSurvival.IO.JoinTeamTabCompleter;
import nmt.minecraft.TeamSurvival.IO.SurvivalCommand;
import nmt.minecraft.TeamSurvival.IO.SurvivalTabCompleter;

/**
 * Plugin class.<br />
 * Creates everything and all things and everything.
 * @author Skyler
 *
 */
public class TeamSurvivalPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("jointeam").setExecutor(new JoinTeamCommand());
		this.getCommand("jointeam").setTabCompleter(new JoinTeamTabCompleter());
		this.getCommand("teamsurvival").setExecutor(new SurvivalCommand());
		this.getCommand("teamsurvival").setTabCompleter(new SurvivalTabCompleter());
		this.getCommand("teamsurvivalcreator").setExecutor(new CreationCommand());
		this.getCommand("teamsurvivalcreator").setTabCompleter(new CreationTabCompleter());
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
}
