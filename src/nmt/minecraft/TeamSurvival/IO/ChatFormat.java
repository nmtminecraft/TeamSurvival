package nmt.minecraft.TeamSurvival.IO;

import org.bukkit.ChatColor;

/**
 * Holds standardized chat formats
 * @author Skyler
 *
 */
public enum ChatFormat {
	
	ERROR(ChatColor.DARK_RED),
	WARNING(ChatColor.YELLOW),
	SESSION(ChatColor.DARK_PURPLE),
	TEAM(ChatColor.BLUE),
	IMPORTANT(ChatColor.DARK_GREEN);
	
	private String format;
	
	private ChatFormat(ChatColor color, ChatColor ... colors) {
		this.format = "" + color;
		if (colors.length > 0) {
			for (ChatColor c : colors) {
				format += "" + c;
			}
		}
	}
	
	@Override
	public String toString() {
		return format;
	}
}
