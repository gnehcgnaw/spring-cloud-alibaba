package red.reksai.providergithubservice.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import red.reksai.providergithubservice.entity.GithubSearchUserList;
import red.reksai.providergithubservice.entity.GithubStarsInfo;
import red.reksai.providergithubservice.util.RestResult;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-05-06 16:01
 * @since :
 */
@Slf4j
@RequestMapping("/github-stars")
@RestController
public class GithubStarsController {
    private RestTemplate restTemplate ;

    public GithubStarsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取指定用户的列表
     * @return
     */
    @GetMapping("/get-all")
    public RestResult<List<GithubStarsInfo>> getAll(String login){
        RestResult<List<GithubStarsInfo>> githubStarsInfoRestResult = new RestResult<>();
        if (login==null){
            githubStarsInfoRestResult.setData(null);
            githubStarsInfoRestResult.setCode(0);
            githubStarsInfoRestResult.setMessage("用户名不能为空");
            return githubStarsInfoRestResult ;
        }else {
            try{
                ResponseEntity<GithubSearchUserList> searchUserListResponseEntity = restTemplate.getForEntity("https://api.github.com/search/users?q=" +login+ "user:"+login, GithubSearchUserList.class);
                GithubSearchUserList githubSearchUserList = searchUserListResponseEntity.getBody();
                if(githubSearchUserList!=null){
                    ResponseEntity<List> list = restTemplate.getForEntity("https://api.github.com/users/"+githubSearchUserList.getItems().get(0).getLogin()+"/starred", List.class);
                    List<GithubStarsInfo> githubStarsInfoList = list.getBody();
                    githubStarsInfoRestResult.setData(githubStarsInfoList);
                    githubStarsInfoRestResult.setMessage("查询成功");
                    githubStarsInfoRestResult.setCode(1);
                    return githubStarsInfoRestResult ;
                }else{
                    githubStarsInfoRestResult.setData(null);
                    githubStarsInfoRestResult.setMessage("用户不存在");
                    githubStarsInfoRestResult.setCode(0);
                    return githubStarsInfoRestResult ;
                }
            }catch (Exception ex){
                githubStarsInfoRestResult.setData(null);
                githubStarsInfoRestResult.setCode(0);
                githubStarsInfoRestResult.setMessage("用户不存在");
                return githubStarsInfoRestResult ;
            }

        }

    }

    @GetMapping("/export-github-stars-excel")
    public RestResult<String> exportGithubStarsExcel(String login){
        log.debug("导出开始");
        RestResult<String> restResult = new RestResult<>();
        RestResult<List<GithubStarsInfo>> listRestResult = getAll(login);
        if (listRestResult.getData()!=null&&listRestResult.getData().size()>0){
            List<GithubStarsInfo> listRestResultData = listRestResult.getData();
            List<GithubStarsInfo> licenses = new Gson().fromJson(new Gson().toJson(listRestResultData), new TypeToken<List<GithubStarsInfo>>(){}.getType());
            OutputStream outputStream = null ;
            String path = "/Users/gnehcgnaw/Desktop/"+"github_stars_excel_"+LocalDate.now() +".xlsx";
            try {

                outputStream = new FileOutputStream(path);
                ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
                Sheet sheet1 = new Sheet(1,0, GithubStarsInfo.class);
                sheet1.setSheetName("第一个sheet");
                excelWriter.write(licenses,sheet1);
                excelWriter.finish();
                restResult.setCode(1);
                restResult.setData(null);
                restResult.setMessage("导出成功"+path);
                log.debug("导出完成");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                restResult.setCode(0);
                restResult.setData(null);
                restResult.setMessage("导出失败");
            }finally {
                try {
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return restResult ;

    }


}
