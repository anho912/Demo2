package com.an.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.an.entity.Dictionary;
import com.an.entity.Product;
import com.an.service.DictionaryService;
import com.an.utils.Results;
import com.an.vo.DatatablesViewPage;

/**
 * 类目控制器类
 * 
 * @author 疯狂的蜗牛君_
 *
 */
@Controller
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 分页查询
	 * 
	 * @param searchDicStr
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/DicData", method = RequestMethod.GET)
	@ResponseBody
	Results DicData(String searchDicStr, int page) {
		String searchStr = "%" + searchDicStr + "%";
		// 获取符合条件的总条数
		long total = dictionaryService.selectCount(searchStr);
		// 计算总页数
		long totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;
		// 查询当页数据
		List<Dictionary> dictionaries = dictionaryService.findDicDataByPageAndCount(10, page, searchStr);
		DatatablesViewPage datatablesViewPage = new DatatablesViewPage(dictionaries, totalPage, total);
		return Results.ok("查询成功").put("data", datatablesViewPage);
	}

	/**
	 * 添加一个商品类目
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "/postDic", method = RequestMethod.POST)
	@ResponseBody
	Results postDic(Dictionary dictionary) {
		dictionary.setCreateDate(new Date(System.currentTimeMillis()));
		Dictionary dictionary2 = dictionaryService.findByDicName(dictionary.getDdName());
		if (dictionary2 == null) {
			int sqltype = dictionaryService.saveDictionary(dictionary);
			if (sqltype == 1) {
				return Results.ok("添加成功！");
			}
			return Results.error("添加失败！");
		}
		return Results.error("该类目已存在，添加失败！");
	}

	/**
	 * 删除商品类目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteDic", method = RequestMethod.GET)
	@ResponseBody
	Results deleteDic(@RequestParam("selectFlags") String[] ids) {
		for (String id : ids) {
			int num = dictionaryService.deleteById(Integer.valueOf(id));
			if (num != 1) {
				return Results.error("删除失败");
			}
		}
		return Results.ok();
	}

	/**
	 * 查询一个商品类目去编辑
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneDic", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getOneDic(@RequestParam("selectFlag") String id) {
		ModelAndView modelAndView = new ModelAndView();
		Dictionary dictionary = dictionaryService.findById(Integer.valueOf(id));
		if (dictionary != null) {
			modelAndView.addObject("dictionary", dictionary);
			modelAndView.setViewName("form_edit_dictionary");
			return modelAndView;
		} else {
			modelAndView.addObject("info", "商品不存在");
			modelAndView.setViewName("form_dictionary");
			return modelAndView;
		}
	}

	/**
	 * 编辑一个商品类目
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/editDic", method = RequestMethod.POST)
	@ResponseBody
	Results editDic(Dictionary dictionary) {
		dictionary.setCreateDate(new Date(System.currentTimeMillis()));
		Dictionary dictionary2 = dictionaryService.findByDicName(dictionary.getDdName());
		if (dictionary2 == null) {
			int sqltype = dictionaryService.updateDic(dictionary);
			if (sqltype == 1) {
				return Results.ok("修改成功！");
			}
			return Results.error("修改失败！");
		}
		return Results.error("该类目已存在，添加失败！");
	}
	
	@RequestMapping(value="/getDictionary",method = RequestMethod.GET)
    @ResponseBody
    public List<Dictionary> getDealer() {
        return dictionaryService.findAll();
    }
}
