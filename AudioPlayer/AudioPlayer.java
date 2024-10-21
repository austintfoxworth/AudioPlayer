import java.io.File;
import java.util.Scanner;

public class AudioPlayer
{
   private static final String[] VALID_COMMANDS = {"PLAY", "STOP", "LIST", "INFO", "EXIT"};
   private static final String[] VALID_AUDIO_INPUT_TYPES = {"NETWORK", "DISK"};
   private static final String EXIT_COMMAND = "EXIT";
   private static final String NO_AUDIO_INPUT_TYPE = "NONE";
   
	public static void main(String[] args) throws Exception
   {
      System.out.println("Audio Player: initializing...");
      
      Scanner kb = new Scanner(System.in);
      
      String command = "";
      
      // setup the audio type
      String audioInputType = getAudioInputType(kb);
      
      GenericAudioInput audio = null;
      
      if (audioInputType.equals(NO_AUDIO_INPUT_TYPE))
         command = EXIT_COMMAND;
      else
      {
         System.out.println("Audio is streaming from " + audioInputType);
         
         // set up the GenericAudioInput object
         if (audioInputType.equals("NETWORK"))
            audio = new NetworkAudioInput("192.168.1.1", 6666);
            
         if (audioInputType.equals("DISK"))
            audio = new DiskAudioInput(new File("hello.txt"));
      }
      
      while (!command.equals(EXIT_COMMAND))
      {
         // keep prompting the user for input
         command = getCommand(kb);
                 
         System.out.println(audio.parseCommand(command));
      }

      // todo: stop the music, close the audioinput, clean up, etc. before exiting
      audio.cleanup();
      
      kb.close();
            
      System.out.println("Goodbye.");      
	}
   
   public static String getCommand(Scanner kb)
   {
      String command = "";
      
      boolean isValidCommand = false;
      
      while (!isValidCommand)
      {
         // get a command from the user
         System.out.print(">> ");
         String userCommand = kb.nextLine().trim().toUpperCase();
         
         for (String validCommand : VALID_COMMANDS)
         {
            if (userCommand.equals(validCommand))
            {
               command = userCommand;
               isValidCommand = true;
               break;
            }
         }
         
         if (isValidCommand == false)
            System.out.println("You did not enter a valid command. Try again.");
      }
            
      return command;
   }
   
   public static String getAudioInputType(Scanner kb)
   {
      String audioInputType = "";
      
      for (String validAudioInputType : VALID_AUDIO_INPUT_TYPES)
      {
         System.out.print("Get music from " + validAudioInputType.toLowerCase() + "? (y/n) ");
         
         String choice = kb.next().trim().toLowerCase();
         
         kb.nextLine(); // flush the buffer
           
         if (choice.equals("y"))
         {
          return validAudioInputType;
         }
      }
    
      System.out.println("You have chosen...poorly.");
      
      return NO_AUDIO_INPUT_TYPE;  
   }
}
