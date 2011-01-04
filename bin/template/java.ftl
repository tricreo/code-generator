<#if classMetaModel.packageName??>
package ${classMetaModel.packageName};
</#if>

public class ${classMetaModel.className} {
<#list classMetaModel.fieldMetaModels as f>
	private ${f.typeName} ${f.fieldName};
	public void set${f.fieldName?cap_first}(${f.typeName} ${f.fieldName}){
		this.${f.fieldName} = ${f.fieldName};
	}
 	public ${f.typeName} get${f.fieldName?cap_first}(){
		return ${f.fieldName};
	}  	    
</#list>
}