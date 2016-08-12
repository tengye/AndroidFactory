package com.teng.androidfactory.model;

import java.util.List;

/**
 * Created by teng on 16/8/12.
 */
public class MenuModel {

    private String totalNum;
    private String pn;
    private String rn;

    private List<Menu> data;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public List<Menu> getData() {
        return data;
    }

    public void setData(List<Menu> data) {
        this.data = data;
    }

    public class Menu{


        /**
         * id : 334
         * title : 西红柿炒蛋
         * tags : 家常菜;美容;延缓衰老;防辐射;防癌;抗癌;消化不良;夏季;开胃;抗衰老;抗氧化;补铁;促消化;健脾;防晒;凉血;清热解暑;健脾胃;缓解压力;脾虚
         * imtro : 这三种健康食材的强强联合，功效绝对不可小觑，肠胃感到不舒服的同学，不妨试一试，保证你的肠胃会很享受。肠胃舒服了，我们才会舒服，才有更坚强的后盾让我们坚定不移地继续吃下去… 红的、黄的、绿的、白的…看起来很缤纷吧，番茄独特的酸味还可以为豇豆大大增味，加上蒜香，超级无敌下饭呐。啥也别说了，速速来碗大米饭，坐下来，静静享受这缤纷味道带给我们的花样心情吧。
         * ingredients : 西红柿,1个;鸡蛋,2个;豇豆,2根;蒜,2瓣
         * burden : 盐,1/2茶匙;蚝油,1/2茶匙;料酒,1/2茶匙
         * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/334_387765.jpg"]
         * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_63130d15d4da82d3.jpg","step":"1.豇豆洗净后切成3厘米长的段，西红柿洗净切块，蒜切片；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_fe1731f1f4eec6a8.jpg","step":"2.鸡蛋打散，调入料酒和一点点清水，搅拌均匀；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_5e8241e44b68fa40.jpg","step":"3.锅烧热倒入少许油，待油8成热时，倒入鸡蛋，用铲子快速画圈拌炒，炒至蛋液凝固结块后，从锅中盛出备用；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_077900045898d50c.jpg","step":"4.另起锅，再次加入少许油，油7成热时，将豇豆倒入翻炒2分钟；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_e77c463574391281.jpg","step":"5.再放入西红柿炒1分钟；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_920b2efaa4a8f39f.jpg","step":"6.放入蒜片，淋入少许清水，盖上盖子，用中小火焖3分钟左右；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_5153a823d90517b2.jpg","step":"7.打开盖子，淋入少许蚝油；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_0e68b9ee5985bf14.jpg","step":"8.再调入适量盐；"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_b6ee057558b333df.jpg","step":"9.最后将炒好的鸡蛋倒入，转大火迅速翻炒1分钟，待汤汁略干即可。"}]
         */

        private String id;
        private String title;
        private String tags;
        private String imtro;
        private String ingredients;
        private String burden;
        private List<String> albums;
        /**
         * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/334_63130d15d4da82d3.jpg
         * step : 1.豇豆洗净后切成3厘米长的段，西红柿洗净切块，蒜切片；
         */

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getImtro() {
            return imtro;
        }

        public void setImtro(String imtro) {
            this.imtro = imtro;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getBurden() {
            return burden;
        }

        public void setBurden(String burden) {
            this.burden = burden;
        }

        public List<String> getAlbums() {
            return albums;
        }

        public void setAlbums(List<String> albums) {
            this.albums = albums;
        }


    }

}
