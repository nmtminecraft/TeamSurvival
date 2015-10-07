package nmt.minecraft.TeamSurvival.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import nmt.minecraft.TeamSurvival.TeamSurvivalPlugin;
import nmt.minecraft.TeamSurvival.Util.LocationState;

/**
 * This Class represents a single map within Team Survival.<br />
 * Each map will contain a Shop Location and a Collection of Locations<br>
 * for players to spawn in.
 * @author William
 * @author Stephanie
 */
public class Map {
	private String name;
	private Location startingLocation;
	private Location shopLocation; //This is the Location of the Shop within Team Survival
	private Location shopButtonLocation; //Location of the Shop Button
	private Collection<Location> arenaLocations;//This collection contains all the initial spawn locations for the Map.
	
	protected Map(String name) {
		super();
		this.name=name;
		
		//create new config file
	}
		
	private Map(){
		super();
	}
	
	/**
	 * This method sets the Shop Location for the Map.
	 * @param location The Location of the Shop.
	 */
	public void setShopLocation(Location location) {
		this.shopLocation = location;
	}
	
	public void setShopButton(Location shopButton) {
		shopButtonLocation = shopButton;
	}

	public void setStartingLocation(Location startingLocation) {
		this.startingLocation = startingLocation;
	}

	/**
	 * This adds a single location to this Map's Arena Locations.
	 * @param location The location to add.
	 * @return True if the location was successfully added.
	 */
	public boolean addArenaLocation(Location location) {
		return this.arenaLocations.add(location);
	}
	
	/**
	 * This method adds multiple locations to this Map's Arena Locations.
	 * @param locations The Collection of Locations to add.
	 * @return True if all the locations could be added.
	 */
	public boolean addArenaLocation(Collection<Location> locations) {
		return this.arenaLocations.addAll(locations);
	}
	
	/**
	 * This method returns the Arena Locations of this Map.
	 * @return A collection of all the Arena Locations.
	 */
	public Collection<Location> getArenaLocations()	{
		return this.arenaLocations;
	}
	
	/**
	 * This method returns the Shop Location of this Map.
	 * @return
	 */
	public Location getShopLocation() {
		return this.shopLocation;
	}
	
	public Location getShopButtonLocation() {
		return this.shopButtonLocation;
	}

	public Location getStartingLocation() {
		return startingLocation;
	}

	public String getName(){
		return name;
	}

	public Location getShopButton() {
		return shopButtonLocation;
	}



	/**
	 * This static method prints out all the yml configuration files<br />
	 * that could possibly be loaded into a map.
	 */
	public static List<String> listConfigs() {
		File resourceFolder = TeamSurvivalPlugin.plugin.getDataFolder();
		File[] resourceFiles = resourceFolder.listFiles();
		Pattern ymlPattern = Pattern.compile("*.yml");
		
		List<String> configFilenames = new LinkedList<String>();
		for (File file : resourceFiles) {
			//Get the Filename
			String fileName = file.getName();
			//Search filename for the .yml extension
			Matcher isYml = ymlPattern.matcher(fileName);
			if (isYml.find()) {
				//Remove .yml extension
				String cleanString = (fileName.split("."))[0];
				configFilenames.add(cleanString);
			}
		}
		return configFilenames;
	}
	
	public static Map loadConfig(String name){
		Map tmp = new Map();
		tmp.name=name;
		
		File file = new File(TeamSurvivalPlugin.plugin.getDataFolder(), name+".yml");
		
		YamlConfiguration config = new YamlConfiguration();
		
		try {
			config.load(file);
			tmp.startingLocation = ((LocationState)config.get("startLocation")).getLocation();
			tmp.shopButtonLocation = ((LocationState)config.get("shopButtonLocation")).getLocation();
			tmp.shopLocation = ((LocationState)config.get("startLocation")).getLocation();
			
			//get the arena locations
			//TODO really not sure if this will work
			@SuppressWarnings("unchecked")
			Collection<LocationState> arenas = (Collection<LocationState>) config.getList("arenaLocations");
			for(LocationState l : arenas){
				tmp.arenaLocations.add(l.getLocation());
			}
			
			return tmp;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
