import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RandomReactLA extends ListenerAdapter
{

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot())
            return;

        if((Math.random()*1000)<10)
            event.getMessage().addReaction("\uD83D\uDC4E").queue();
        else
            if((Math.random()*1000)<10)
                event.getMessage().addReaction("\uD83D\uDC4D").queue();

        if((Math.random()*1000)<10)
        {
            if (event.getAuthor().getName().equals("Red"))
            {
                event.getMessage().addReaction("\uD83C\uDDED").queue();
                event.getMessage().addReaction("\uD83C\uDDEA").queue();
                event.getMessage().addReaction("\uD83C\uDDF3").queue();
                event.getMessage().addReaction("\uD83C\uDDF9").queue();
                event.getMessage().addReaction("\uD83C\uDD70").queue();
                event.getMessage().addReaction("\uD83C\uDDEE").queue();
            }

            if (event.getAuthor().getName().equals("LordEnglish"))
            {
                event.getMessage().addReaction("\uD83C\uDD71").queue();
                event.getMessage().addReaction("\uD83C\uDD70").queue();
                event.getMessage().addReaction("\uD83C\uDDF0").queue();
                event.getMessage().addReaction("\uD83C\uDDE6").queue();
            }
        }
    }
}
