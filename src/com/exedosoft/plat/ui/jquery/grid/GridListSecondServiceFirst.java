package com.exedosoft.plat.ui.jquery.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.DOViewTemplate;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;

/**
 * @author aa
 */
public class GridListSecondServiceFirst extends DOViewTemplate {

	private static Log log = LogFactory.getLog(GridListSecondServiceFirst.class);

	public GridListSecondServiceFirst() {
		this.templateFile = "grid/GridList.ftl";
	}

	public Map<String, Object> putData(DOIModel doimodel) {

		DOGridModel gm = (DOGridModel) doimodel;
		if (gm.getService() == null || gm.getSecondService() == null) {
			log.info("Error ����� ��ִ�еڶ����񣬳���ԭ�򣺵�һ�����ڶ��������Ϊ�ա�");
			return null;
		} 
		DOService secondService = gm.getSecondService();
		try {
			secondService.currentTransaction().begin();
			secondService.invokeUpdate();
			secondService.currentTransaction().end();
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			log.info("Error �����ǰ��ִ�еڶ����񣬳�����");
			e.printStackTrace();
		}
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", gm);
		data.put("data", getListData(gm, data));
		data.put("webmodule", DOGlobals.URL);
		data.put("contextPath", DOGlobals.PRE_FULL_FOLDER);
		if(gm.getContainerPane()!=null){
			data.put("pmlName", gm.getContainerPane().getName());
		}
		data.put("formName", "a" + gm.getObjUid());

		if (gm.getContainerPane()!=null && gm.getContainerPane().getParent() != null) {

			////�Զ��ж��������
			List children = gm.getContainerPane().getParent()
					.retrieveChildren();

			if (children != null && children.size() == 2) {
				DOPaneModel conditionPane = (DOPaneModel) children.get(0);
				DOPaneModel resultModel = (DOPaneModel) children.get(1);
				if (conditionPane != null) {
					if (conditionPane.getDOGridModel() != null) {
						String formName = "a"
								+ conditionPane.getDOGridModel().getObjUid();
						data.put("formName", formName);
					}
				}

			}
						
			////���������������壨����ĺ�����  ӵ�б�������壩
			DOPaneModel hpm =gm.getContainerPane().getHiddenPane(); 
			if(hpm!=null){
				if (hpm.getDOGridModel() != null) {
					String formName = "a"
							+ hpm.getDOGridModel().getObjUid();
					data.put("formName", formName);
				}
			}
			
		}

		return data;
	}

	public static List<BOInstance> getListData(DOGridModel gridModel,
			Map<String, Object> data) {
		List<BOInstance> list;
		int pageNo = 1;
		int pageNum = 0;

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance()!=null && DOGlobals.getInstance().getSessoinContext().getFormInstance()
				.getValue("pageNo") != null) {
			try {
				DOGlobals go = DOGlobals.getInstance();
				SessionContext sess = DOGlobals.getInstance().getSessoinContext();
				BOInstance bi =	DOGlobals.getInstance().getSessoinContext().getFormInstance();
				
				pageNo = Integer.parseInt(DOGlobals.getInstance()
						.getSessoinContext().getFormInstance().getValue("pageNo"));
			} catch (Exception e) {

			}
		}
		// pageNo = DOGlobals.getInstance().getSessoinContext().splitPageContext
		// .getPageNo(gridModel.getService());
		// log.info("SplitPage Filter Table Get PageNO:::" + pageNo);

		if (gridModel.getRowSize() != null) {
			pageNum = gridModel.getRowSize().intValue();
		}

		if (pageNum <= 0) {
			list = gridModel.getService().invokeSelect();
		} else {
			data.put("rowSize", pageNum);
			int resultSize = gridModel.getService().getResultSize();
			int pageSize = StringUtil.getPageSize(resultSize, pageNum);
			data.put("pageSize", pageSize);
			data.put("resultSize", resultSize);
			data.put("pageNo", pageNo);

			list = gridModel.getService().invokeSelect(pageNo, pageNum);

		}
		return list;
	}

}