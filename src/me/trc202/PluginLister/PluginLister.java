package me.trc202.PluginLister;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLister extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	static String mainDirectory = "plugins/PluginLister";
	public static File pluginlistfile = new File(mainDirectory + File.separator + "plugins.txt");
	@Override
	public void onDisable() {
		log.info("[" + this.getDescription().getName() + "]" + " is disabled.");
	}

	@Override
	public void onEnable() {
		log.info("[" + this.getDescription().getName() + "]" + " is enabled.");
		updatePluginList();
	}
	private void updatePluginList()
	{
		PluginManager pm = getServer().getPluginManager();
		try {
			new File(mainDirectory).mkdir();
			if(!pluginlistfile.exists())
			{
				pluginlistfile.createNewFile();
				log.info("[" + this.getDescription().getName() + "]" + "Created plugin list file");
			}
			FileWriter fstream = new FileWriter(pluginlistfile);
			BufferedWriter out = new BufferedWriter(fstream);
			
			
		Plugin mypluginlist[] = pm.getPlugins();
		for(Plugin myplug : mypluginlist)
		{
			if(myplug.getDescription().getName() != null)
			{
				out.append("Plugin Name: " + myplug.getDescription().getName() + System.getProperty("line.separator"));
			}
			if(myplug.getDescription().getVersion() != null)
			{
				out.append("Version: " + myplug.getDescription().getVersion() + System.getProperty("line.separator"));
			}
			if(myplug.getDescription().getAuthors().isEmpty() == false)
			{
				if(myplug.getDescription().getAuthors().size() > 1)
				{
					String AuthorList = "";
					for(int i = 0; i < myplug.getDescription().getAuthors().size(); i++)
					{
						String Author = myplug.getDescription().getAuthors().get(i);
						AuthorList = AuthorList + " " + Author;
						if(!(i + 1 > myplug.getDescription().getAuthors().size()))
						{
							AuthorList = AuthorList + ",";
						}
						
					}
					out.append("Authors: " + AuthorList + System.getProperty("line.separator"));
				}
				else
				{	
					String AuthorName = myplug.getDescription().getAuthors().get(0);
				out.append("Author: " + AuthorName + System.getProperty("line.separator"));
				}
			}
			if(myplug.getDescription().getWebsite() != null)
			{
				out.append("Webpage: " + myplug.getDescription().getWebsite() + System.getProperty("line.separator"));
			}
			out.append(System.getProperty("line.separator"));
			
		}
		log.info("[" + this.getDescription().getName() + "]" + "Updated plugin list");
		out.flush();
		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
		if( sender instanceof ConsoleCommandSender)
		{
			if(command.getName().equalsIgnoreCase("PlugList") || command.getName().equalsIgnoreCase("PluginLister"))
			{
				if((args != null && args.length != 0))
				{
					if(args.length == 1)
					{
						if(args[0].equalsIgnoreCase("log"))
						{
							printpluginstolog();
							return true;
						}
					}
				}
				else
				{
					updatePluginList();
					return true;
				}
					
			}
		}
		else if(sender instanceof Player)
		{
			Player plr = (Player) sender;
			plr.sendMessage(ChatColor.RED + "Only the console can use this command");
			return true;
		}
		else
		{
			
		}
		return false;
	}

	private void printpluginstolog() {
		PluginManager pm = getServer().getPluginManager();
		Plugin mypluginlist[] = pm.getPlugins();
		for(Plugin myplug : mypluginlist)
		{
			if(myplug.getDescription().getName() != null)
			{
				log.info("Plugin Name: " + myplug.getDescription().getName() + System.getProperty("line.separator"));
			}
			if(myplug.getDescription().getVersion() != null)
			{
				log.info("Version: " + myplug.getDescription().getVersion() + System.getProperty("line.separator"));
			}
			if(myplug.getDescription().getAuthors().isEmpty() == false)
			{
				if(myplug.getDescription().getAuthors().size() > 1)
				{
					String AuthorList = "";
					for(int i = 0; i < myplug.getDescription().getAuthors().size(); i++)
					{
						String Author = myplug.getDescription().getAuthors().get(i);
						AuthorList = AuthorList + " " + Author;
						if(!(i + 1 > myplug.getDescription().getAuthors().size()))
						{
							AuthorList = AuthorList + ",";
						}
						
					}
					log.info("Authors: " + AuthorList + System.getProperty("line.separator"));
				}
				else
				{	
					String AuthorName = myplug.getDescription().getAuthors().get(0);
					log.info("Author: " + AuthorName + System.getProperty("line.separator"));
				}
			}
			if(myplug.getDescription().getWebsite() != null)
			{
				log.info("Webpage: " + myplug.getDescription().getWebsite() + System.getProperty("line.separator"));
			}
			log.info(System.getProperty("line.separator"));
			
		}
		
	}
	
}