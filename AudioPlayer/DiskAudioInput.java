import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class DiskAudioInput extends GenericAudioInput
{
   private File audioFile;

   public DiskAudioInput(File audioFile)
   {
      this.audioFile = audioFile;
      getAudio();
   }
  
   public DiskAudioInput(String fileName)
   {
      File audioFile = new File(fileName);
      
      if (!audioFile.exists() || !audioFile.isFile())
         throw new IllegalArgumentException("Not a file: " + fileName);
         
      getAudio();
   }
   
   protected void getAudio()
   {
      try
      {
         audioInput = new BufferedInputStream( new FileInputStream(audioFile) );
      }
      catch (Exception e)
      {
         System.out.println("Error creating audio input stream.");
      }
   }
}