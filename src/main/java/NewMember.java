import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class NewMember extends ListenerAdapter
{
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event)
    {
        if(event.getUser().getName().equalsIgnoreCase("LordEnglish"))
        {
            event.getGuild().getDefaultChannel().sendMessage("custom message").queue();
            return;
        }
        event.getGuild().getDefaultChannel().sendMessage("Hello "+event.getUser().getName()).queue();
    }
}
