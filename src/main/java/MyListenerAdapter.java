import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.nio.channels.Channel;
import java.time.OffsetDateTime;
import java.util.List;

public class MyListenerAdapter extends ListenerAdapter
{
    boolean isClosed=false;
    static MessageChannel channel=new MessageChannel()
    {
        @Override
        public long getIdLong() {return 0;}

        @Override
        public long getLatestMessageIdLong() {return 0;}

        @Override
        public boolean hasLatestMessage() {return false;}

        @NotNull
        @Override
        public String getName() {return "";}

        @NotNull
        @Override
        public ChannelType getType() {return null;}

        @NotNull
        @Override
        public JDA getJDA() {return null;}
    };

    public static MessageChannel getChannel()
    {
        return channel;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("kill"))
        if(event.getAuthor().getName().equals("Shiro"))
        {
            event.getMessage().addReaction("\uD83D\uDC80").queue();
            //event.getMessage().addReaction("☠").queue();
            System.out.println("Bot was killed by: "+event.getAuthor().getName());

            System.exit(-1);
        }
        else
            System.err.println("Unauthorized use of '"+event.getMessage().getContentRaw()+"' by user: "+event.getAuthor().getName());

        if(event.getMessage().getContentRaw().equalsIgnoreCase("close"))
        {
            isClosed = true;
            event.getMessage().addReaction("✔").queue();
            channel=event.getChannel();
            System.out.println("MyListenerAdapter- set channel: "+channel.getName());
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase("open"))
            isClosed=false;

        if(event.getMessage().getContentRaw().equalsIgnoreCase("setchannel"))
        {
            event.getMessage().addReaction("✔").queue();
            channel=event.getChannel();
            System.out.println("MyListenerAdapter- set channel: "+channel.getName());
            //channel.sendMessage("Ustawiono aktywny kanał na "+channel.getName()).queue();
        }


        if(event.getMessage().getContentRaw().equalsIgnoreCase("where is cholibka"))
        {
            if(channel.getName().isEmpty())
                event.getChannel().sendMessage("Cholibka has not been set yet").queue();
            else
                event.getChannel().sendMessage("Cholibka is active only in " + channel.getName()).queue();
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("help"))
        {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Commands:");
            info.setDescription("\n" +
                    "alphabetically -return arguments of the command in alphabetical order\n"+
                    "close -will restrict most of bot functions to one channel\n" +
                    "coin -flip a coin\n" +
                    "help -dude you just type it\n" +
                    "lewd -you will see\n" +
                    "open -remove bots restrictions made by 'close'\n" +
                    "ping -that maybe will be working some day\n" +
                    "react -add reaction to this message\n" +
                    "setchannel -set channel you want Cholibka to work in\n" +
                    "stream link -give link to best twitch channel ever\n" +
                    "where is cholibka -get channel where cholibka is working");
            info.setColor(0xf45642);
            info.setFooter("Created by Shiro", event.getMember().getUser().getAvatarUrl());
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear(); 
        }

        if ((!event.getChannel().equals(channel))&&isClosed)
            return;

        if(event.getAuthor().isBot())
            return;

//        System.out.println("Message reaceived from "+event.getAuthor().getName()+
//                " : "+event.getMessage().getContentDisplay());



        switch (event.getMessage().getContentRaw().toLowerCase())
        {
            case "react":
            {
                event.getMessage().addReaction(":cholipka:693146005201223722").queue();
                break;
            }//channel.sendMessage(":cholipka:").queue();
            case "lewd":
            {
                //event.getMessage().addReaction("").queue();
                event.getChannel().sendMessage(event.getAuthor().getName()+" this is christian bot....").queue();
                break;
            }
            case "coin":
            {
                if(Math.random()>0.5)
                    event.getChannel().sendMessage("Head").queue();
                else
                    event.getChannel().sendMessage("Tail").queue();
                break;
            }
            case "stream link":
            {
                event.getChannel().sendMessage("https://www.twitch.tv/fawxicity").queue();
                break;
            }
            case "ping":
            {
                MessageChannel channel = event.getChannel();
                long time = System.currentTimeMillis();
                channel.sendMessage("Pong: %d ms"+ (System.currentTimeMillis() - time)).queue();
                break;
            }

            case "pong":
            {
                event.getChannel().sendMessage("You mean 'ping'?").queue();
                break;
            }
            default:
            {
                if(Math.random()*1000<5)
                {
                    int numerWiadomosci=(int)(Math.random()*100);
                    switch (numerWiadomosci)
                    {
                        case 0:
                            channel.sendMessage("baka...").queue();
                            break;
                        case 1:
                            channel.sendMessage("www.youtube.com/watch?v=dQw4w9WgXcQ").queue();
                            break;
                        case 2:
                            channel.sendMessage("widzisz i nie grzmisz....").queue();
                            break;
                        case 3:
                            channel.sendMessage("łobuzie!").queue();
                            break;
                        case 4:
                            channel.sendMessage("hultaju!").queue();
                            break;
                        case 5:
                            channel.sendMessage("ty piracie!").queue();
                            break;
                        case 6:
                            channel.sendMessage("zabójco królowej anglii! \nta.. akurat to jest możliwe").queue();
                            break;


                        default:
                        {
                            channel.sendMessage("Wy tu sobie gadacie a ja musze wszystko scannować! Zero spokoju...").queue();
                        }

                    }
                }
            }

        }
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event)
    {
        System.out.println("Im ready");
    }



    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event)
    {
        User user= event.getUser();
        user.openPrivateChannel().queue((channel) ->
        {
            // Send a private message to the user
            channel.sendMessageFormat("https://cdn.discordapp.com/attachments/224879727121268749/739090344897019934/final_5f2556b1d8e3b80015b296db_7340231_1.mp4", event.getGuild().getName()).queue();
        });
    }
}
