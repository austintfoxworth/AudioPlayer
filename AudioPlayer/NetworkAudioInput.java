import java.net.Socket;
import java.io.BufferedInputStream;

public class NetworkAudioInput extends GenericAudioInput
{
   private Socket audioServer;
   
   public NetworkAudioInput(String ipAddress, int portNumber)
   {
      // todo: we need a private function to validate the ip and port number
      try
      {
         audioServer = new Socket(ipAddress, portNumber);
         getAudio();
      }
      catch (Exception e)
      {
      }
   }
  
   public NetworkAudioInput(Socket audioServer)
   {
      this.audioServer = audioServer;
      getAudio();
   }
   
   protected void getAudio()
   {
      try
      {
         if (audioServer.isConnected())
            audioInput = new BufferedInputStream(audioServer.getInputStream(), 2048);
      }
      catch (Exception e)
      {
         System.out.println("Error reading from socket.");
      }
   }
}