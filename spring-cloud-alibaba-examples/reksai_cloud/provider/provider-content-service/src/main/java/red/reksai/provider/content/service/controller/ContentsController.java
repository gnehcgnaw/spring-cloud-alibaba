package red.reksai.provider.content.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import red.reksai.provider.content.service.daomain.TbContent;
import red.reksai.provider.content.service.service.TbContentService;
import red.reksai.provider.content.service.util.RestResult;

import java.util.List;

/**
 * @author : <a href="mailto:gnehcgnaw@gmail.com">gnehcgnaw</a>
 * @date : 2019-06-09 17:11
 * @since :
 */
@RestController
public class ContentsController {

    private TbContentService contentService ;

    public ContentsController(TbContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/")
    RestResult<List<TbContent>> list (){
        RestResult<List<TbContent>> listRestResult = new RestResult<>();
        List<TbContent> list = contentService.list();
        if (list!=null && list.size()>0){
            listRestResult.setCode(1);
            listRestResult.setMessage("查询成功");
            listRestResult.setData(list);
        }else{
            listRestResult.setCode(0);
            listRestResult.setMessage("查询失败");
            listRestResult.setData(null);
        }
        return listRestResult ;

    }
}
