import java.io.BufferedInputStream;

public abstract class GenericAudioInput
{
   protected String command = "STOP";

   protected BufferedInputStream audioInput = null;
   
   protected abstract void getAudio();
   
   private void play()
   {
   }
   
   private void stop()
   {
   }
   
   private void info()
   {
   }
   
   private void list()
   {
   }
   
   public String parseCommand(String command)
   {
      this.command = command;
      
      // actually execute the command
      
      return "Complete.";
   }
}