package cn.sakuratown.jeremyhu.sakuraarchitecture.commands;

import cn.sakuratown.jeremyhu.sakuraarchitecture.commands.handlers.CommandHandler;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.StringUtil.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommandExecuter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        try {
            CommandHandler handler = getCommandHandler(args);
            handler.handle(player, args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static CommandHandler getCommandHandler(String[] args) throws Exception {
        if (args.length > 1){
            String name = captureName(args[0]);
            return (CommandHandler) Class.forName(CommandHandler.class.getPackage().getName() + "." + name + "Handler").newInstance();
        }else throw new IllegalArgumentException();
    }
}
