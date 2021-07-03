import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ReactionListiner
        extends ListenerAdapter
{
    @Override
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event)
    {
        if(event.getUser().isBot())
            return;

        if(!event.getReactionEmote().isEmoji())
        {
            try
            {
                throw new CustomEmojiException();
            } catch (CustomEmojiException e)
            {
                System.out.println(e.getMessage());
            }
            return;
        }


        switch (event.getReactionEmote().getEmoji())
        {
            case "✔":
            {
                System.out.println("✔");
                break;
            }
            case "\uD83D\uDC4E":
            {
                String messageID=event.getMessageId();
                event.getReaction().getChannel().addReactionById(messageID,"\uD83D\uDC4E").queue();
                break;
            }
            case"\uD83D\uDC4D":
            {
                String messageID=event.getMessageId();
                event.getReaction().getChannel().addReactionById(messageID,"\uD83D\uDC4D").queue();
                break;
            }
            default:
            {
                System.out.println("Unknown emoji");
            }
        }
    }
}
