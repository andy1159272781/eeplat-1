	package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.StringUtil;

public class DOInputRadio extends DOStaticList {

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		List list = StringUtil.getStaticList(property.getInputConfig());
	    return formSelectStr(property,list);
	}

	String formSelectStr(DOFormModel property, List list) {

		StringBuffer buffer = new StringBuffer();

		String value = property.getValue();

		if (list != null) {
			boolean isFirst = true;
			String defaultValue = getDefaultListValue(property);
			for (Iterator it = list.iterator(); it.hasNext();) {
				String[] half = (String[]) it.next();
				buffer.append(" <input ")
				.append(" type=\"radio\" name=\"").append(property.getFullColName()).append("\" ")
				.append("  value=\"").append(half[0]);
				buffer.append("\"");
				if (isFirst) {
					if (value == null
							&& defaultValue == null
							&& (property.getDefaultValue() != null && !property
									.getDefaultValue().trim().equals(""))) {
						buffer.append(" checked=\"checked\"  ");
					}
					isFirst = false;
				}
				
				if (isReadOnly(property)) {
					buffer.append(" DISABLED  ");
				}
				
				buffer.append(this.appendValidateConfig(property));

				
				if (value != null) { // ////////修改的情况
					if (value.equals(half[0])) {
						buffer.append(" checked=\"checked\"  ");
					}
				} else { // //////添加的情况

					if (defaultValue != null && defaultValue.equals(half[0])) {
						buffer.append(" checked=\"checked\"  ");
					}

				}
				if (property.getDoClickJs() != null
						&& !"".equals(property.getDoClickJs().trim())) {
					buffer.append(" onclick='").append(
							property.getEscapeDOClickJs()).append("'");
				}

				buffer.append("/>")
				.append(half[1]);
				
			}
		}
	
		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		return buffer.toString();
	}

}
