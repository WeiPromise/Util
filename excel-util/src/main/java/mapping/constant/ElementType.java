package mapping.constant;

/**
 * Created by leiwei on 2020/8/6 9:24
 */
public enum ElementType {
    ENTITY("entity","实体"), EVENT("event","事件"), RELATION("relationship","关系");

    private String elementType;
    private String elementTypeCn;

    ElementType(String elementType,String elementTypeCn) {

        this.elementType = elementType;
        this.elementTypeCn=elementTypeCn;
    }

    public String getElementType() {
        return elementType;
    }

    public String getElementTypeCn() {
        return elementTypeCn;
    }

    public static ElementType findByName(String name){
        for(ElementType e : values()){
            if(e.elementType.equals(name)){
                return e;
            }
        }
        return null;
    }
}
