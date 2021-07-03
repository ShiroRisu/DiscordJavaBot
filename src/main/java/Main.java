import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.annotation.Nonnull;
import java.nio.channels.Channel;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String token=args[0];
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.watching("Cyberpunk 2077 Twitch Stream"));
        builder.addEventListeners(new MyListenerAdapter());
        builder.addEventListeners(new RandomReactLA());
        builder.addEventListeners(new ReactionListiner());
        builder.addEventListeners(new ScanningListenerAdapter());

        builder.build();
    }
}
