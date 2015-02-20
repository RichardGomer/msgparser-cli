package net.kolola.msgparsercli;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import com.auxilii.msgparser.*;
import com.auxilii.msgparser.attachment.Attachment;
import com.auxilii.msgparser.attachment.FileAttachment;

public class MsgParseCLI {

	public static void main(String[] args) {
		
		// Parse options

        OptionParser parser = new OptionParser("f:a:bi?*");
        OptionSet options = parser.parse(args);
        
        // Get the filename
        if(!options.has("f"))
        {
        	System.err.print("Specify a msg file with the -f option");
        	System.exit(0);
        }
        
        
        File file = new File((String) options.valueOf("f"));

		MsgParser msgp = new MsgParser();
		Message msg = null;
		
		try
		{
			msg = msgp.parseMsg(file);
		}
		catch (UnsupportedOperationException | IOException e)
		{
			System.err.print("File does not exist or is not a valid msg file");
			//e.printStackTrace();
			System.exit(1);
		}
        
        // Show info (as JSON)
        if(options.has("i"))
        {
			Map<String, Object> data = new HashMap<String, Object>(); 
			
			String date;
			
			try
			{
				Date st = msg.getClientSubmitTime();
				date = st.toString();
			}
			catch(Exception g)
			{
				try
				{
					date = msg.getDate().toString();
				}
				catch(Exception e)
				{
					date = "[UNAVAILABLE]";
				}
			}
			
			data.put("date", date);
			data.put("subject", msg.getSubject());
			data.put("from", "\"" + msg.getFromName() + "\" <" + msg.getFromEmail() + ">");
			data.put("to", "\"" + msg.getToRecipient().toString());
			
			String cc = "";
			for(RecipientEntry r : msg.getCcRecipients())
			{
				if(cc.length() > 0)
					cc.concat("; ");
				
				cc.concat(r.toString());
			}
			
			data.put("cc", cc);
			
			data.put("body_html", msg.getBodyHTML());
			data.put("body_rtf", msg.getBodyRTF());
			data.put("body_text", msg.getBodyText());
			
			// Attachments
			List<Map<String, String>> atts = new ArrayList<Map<String,String>>();
			for(Attachment a : msg.getAttachments())
			{
				HashMap<String, String> info = new HashMap<String, String>();
				
				if(a instanceof FileAttachment)
	        	{
					FileAttachment fa = (FileAttachment) a;
					
					info.put("type", "file");
					info.put("filename", fa.getFilename());
					info.put("size",  Long.toString(fa.getSize()));
	        	}
				else
				{
					info.put("type", "message");
				}
				
				atts.add(info);
			}
			
			data.put("attachments", atts);
			
			JSONObject json = new JSONObject(data);
			
			try
			{
				System.out.print(json.toString(4));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
        }
        
        // OR return an attachment in BASE64
        else if(options.has("a"))
        {
        	Integer anum = Integer.parseInt((String) options.valueOf("a"));
        	
        	Encoder b64 = Base64.getEncoder();
        	
        	List<Attachment> atts = msg.getAttachments();
        	
        	if(atts.size() <= anum)
        	{
        		System.out.print("Attachment " + anum.toString() + " does not exist");
        	}
        	
        	Attachment att = atts.get(anum);
        	
        	if(att instanceof FileAttachment)
        	{
        		FileAttachment fatt = (FileAttachment) att;
        		System.out.print(b64.encodeToString(fatt.getData()));
        	}
        	else
        	{
        		System.err.print("Attachment " + anum.toString() + " is a message - That's not implemented yet :(");
        	}
        }
        // OR print the message body
        else if(options.has("b"))
        {
        	System.out.print(msg.getConvertedBodyHTML());
        }
        else
        {
        	System.err.print("Specify either -i to return msg information or -a <num> to print an attachment as a BASE64 string");
        }
       
	}
	
	protected static void help() {
		System.err.print("Msg Parser CLI\n(C)2015 KOLOLA Limited www.kolola.net\nBased on the msgparser library from http://auxilii.com/msgparser/\nLicensed under GPL 3.0\n\n");
		
	}

}
