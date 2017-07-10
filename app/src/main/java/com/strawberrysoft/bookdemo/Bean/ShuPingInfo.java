package com.strawberrysoft.bookdemo.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ShuPingInfo {
    String[] title = {"我家没有龙——关于一部糟糕的先锋小说集的阅读报告","我的朋友方悄悄","走出虚无：从安妮宝贝到庆山","精神医学界的“泥石流”","如何实现自己想要的生活？"};
    String[] content = {
            "其实我在读完这本书的第一篇之后就准备放弃了，但想起之前有不少朋友向我推荐过孙一圣的小说，又看到腰封上英国《卫报》称其为“中国最具潜力作者”，著名翻译家",
            "这个人的小说里，都没有坏人的，连个真正让人讨厌的人都没有。这样的人，现实生活中怎么可能不苦逼？",
            "安妮宝贝更名为庆山之后的另一部重磅作品《月童度河》于日前出版。作为安妮宝贝多年的读者与批评者，我一向不喜欢安妮宝贝的作品，也不带什么好感。但必须诚实地说，《月童度河》给我带来了出乎意料的阅读体验。简单地讲",
            " “4∶48精神崩溃”---据统计绝大多数的自杀事件都发生在凌晨4点48分，因为在这一时刻人的精神错乱达到极致。（人类在白天和黑夜果然有着不同的神经。",
            "通常大家认为追求自己想要的生活会很累，得过且过比较轻松，丹尼斯·韦特利（Denis Waitley）在《成功心理学：发现工作与生活的意义》明确提出“为不称心的生活耗费的精力和为令人满意的生活付出的一样多。 ”"
    };
    String[] author = {"远子","荞麦","曾于里","茜茜","鼹鼠的土豆"};
    String[] book = {"《你家有龙多少回》","《看了高兴的爱情故事》","《月童渡河》","《精神科的故事：空中秋千》","《成功心理学》"};
    String[] img = {
            "https://img3.doubanio.com/lpic/s29005062.jpg",
            "https://img1.doubanio.com/lpic/s28950988.jpg",
            "https://img3.doubanio.com/lpic/s28825570.jpg",
            "https://img3.doubanio.com/lpic/s28939295.jpg",
            "https://img3.doubanio.com/lpic/s28774442.jpg"
    };
    int[] zan = {1586,1429,5631,4210,4795};
    int[] ping = {243,261,352,671,129};
    public List<ShuPingBean> getShuPing(){
        List<ShuPingBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i ++){
            ShuPingBean bean = new ShuPingBean(title[i],content[i],author[i],book[i],img[i],zan[i],ping[i]);
            list.add(bean);
        }
        return list;
    }
}
