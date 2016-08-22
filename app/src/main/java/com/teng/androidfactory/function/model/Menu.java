package com.teng.androidfactory.function.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by teng on 16/8/11.
 */
@AutoValue
public abstract class Menu {

    /**
     * id : 307
     * title : 西红柿炖牛肉
     * tags : 家常菜;美容;延缓衰老;补血;防辐射;增肥;阳虚质;贫血;防癌;抗癌;骨质疏松;炖;消化不良;开胃;增强抵抗力;抗衰老;补钙;抗氧化;提高免疫力;补铁;促消化;健脾;防晒;凉血;清热解暑;祛风散寒;祛寒;健脾养胃;补虚;贴秋膘;缓解压力;强筋健骨;增高;脾虚
     * imtro : 西红柿 - 西红柿含有丰富的营养，又有多种功用被称为神奇的菜中之果。它所富含的维生素a原，在人体内转化为维生素a，能促进骨骼生长，防治佝偻病、眼干燥症。 　　 牛肉 - 牛肉富含肌氨酸：牛肉中的肌氨酸含量比任何其它食品都高，这使它对增长肌肉、增强力量特别有效。 西红柿炖牛肉是以西红柿和牛肉为主要食材的家常菜，口味清淡，补铁补血，营养价值丰富。
     * ingredients : 牛肉,500g;西红柿,100g
     * burden : 油,适量;盐,适量;生抽,适量;大葱,适量;大料,适量;料酒,适量
     */

    public abstract String id();
    public abstract String title();
    public abstract String tags();
    public abstract String imtro();
    public abstract String ingredients();
    public abstract String burden();
    public abstract List<String> albums();

    public static Menu create(String id , String title , String tags , String imtro , String ingredients , String burden , List<String> albums){
        return new AutoValue_Menu(id, title, tags, imtro, ingredients, burden , albums);
    }

    public static TypeAdapter<Menu> typeAdapter(Gson gson) {
        return new AutoValue_Menu.GsonTypeAdapter(gson);
    }

}
