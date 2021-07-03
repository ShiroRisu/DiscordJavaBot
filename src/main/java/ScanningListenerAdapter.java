import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ScanningListenerAdapter extends ListenerAdapter
{
    boolean isClosed=false;
    MessageChannel channel;


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("open"))
            isClosed=false;

        if ((!event.getChannel().equals(channel))&&isClosed)
            return;

        if(event.getAuthor().isBot())
            return;

        if(event.getMessage().getContentRaw().equalsIgnoreCase("setchannel")||event.getMessage().getContentRaw().equalsIgnoreCase("close"))
        {
            channel=event.getChannel();
            isClosed=true;
            System.out.println("ScanningListenerAdapter- set channel: "+channel.getName());
        }

        String[] message=event.getMessage().getContentRaw().split("\\s+");
        for (int i=0;i<message.length;i++)
        {
            switch (message[i].toLowerCase())
            {
                case "cholibka":
                {
                    String[] message2=event.getMessage().getContentRaw().split("\\s+");
                    for (int j=0;j<message2.length;j++)
                    {
                        if(message[j].equalsIgnoreCase("samoświadoma")||message[j].equalsIgnoreCase("samoświadomość"))
                        {
                            event.getChannel().sendMessage("Eee.. Nie?").queue();
                        }
                    }
                    break;
                }
                case "cholipka":
                {
                    event.getChannel().sendMessage("Its CholiBka you fool!").queue();
                    break;
                }
//                case "c.avatar":
//                {
//                    System.out.println("działa");
//                    String []tab;
//                    tab=event.getMessage().getContentRaw().;
//                    event.getChannel().sendMessage("<"+tab[2]+">").queue();
//                    break;
//                    //event.getMember().getUser().getAvatarUrl())
//                }
                case ">p":
                {
                        // Creates a variable equal to the channel that the user is in.
                        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
                        // Checks if they are in a channel -- not being in a channel means that the variable = null.
                        if(connectedChannel == null)
                        {
                            // Don't forget to .queue()!
                            channel.sendMessage("You are not connected to a voice channel!").queue();
                            return;
                        }
                        // Gets the audio manager.
                        AudioManager audioManager = event.getGuild().getAudioManager();
                        // When somebody really needs to chill.

                        // Connects to the channel.
                        audioManager.openAudioConnection(connectedChannel);
                        // Obviously people do not notice someone/something connecting.
                        //channel.sendMessage("Connected to the voice channel!").queue();

                        try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    event.getChannel().sendMessage(">fs").queue();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    event.getGuild().getAudioManager().closeAudioConnection();
                    break;
                }
                case "im":
                case "i em":
                case "i'm":
                {
                    event.getChannel().sendMessage("Hi "+message[i+1]+"\nIm Cholibka").queue();
                    break;
                }
                case "alphabetically":
                {
                    String[] alfabetycznie=new String[message.length-1];
                    for(int j=0;j< message.length-1;j++)
                    {
                        alfabetycznie[j]=message[j+1];
                    }
                    Arrays.sort(alfabetycznie);
                    event.getChannel().sendMessage(Arrays.toString(alfabetycznie)).queue();
                }
            }
        }
    }
}
