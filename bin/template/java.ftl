<#if classMetaModel.packageName??>
package ${classMetaModel.packageName};
</#if>

public class ${classMetaModel.className} {
<#list classMetaModel.fieldMetaModels as f>
	private ${f.typeName} ${f.fieldName};
	public void set${f.fieldName?cap_first}(${f.typeName} ${f.fieldName}){
		this.${f.fieldName} = ${f.fieldName};
	}
<#if f.typeName == "boolean">
<#assign getter = "is"/>
<#elseif f.typeName == "java.lang.Boolean">
<#assign getter = "is"/>
<#else>
<#assign getter = "get"/>
</#if>  	    
 	public ${f.typeName} ${getter}${f.fieldName?cap_first}(){
		return ${f.fieldName};
	}
</#list>
}