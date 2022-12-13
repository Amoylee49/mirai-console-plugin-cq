package method;

import bean.Character;
import bean.CharacterHolder;
import com.fasterxml.jackson.core.type.TypeReference;

import constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.JsonUtils;
import utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Slf4j
public class CharacterCaches {
    private List<CharacterHolder> characters;

    public List<CharacterHolder> getAllCharacters() {
        return characters;
    }

    public boolean cacheAllCharactersFromWeb() {
//        status.clearStatus();
        characters = generateAllCharactersFromWeb();
        return characters.size() != 0;
    }

    public boolean cacheAllCharactersFromJson() {
        characters = generateAllCharactersFromJson();
        return characters.size() != 0;
    }

    private List<CharacterHolder> generateAllCharactersFromJson() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Characters.json")) {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String json = buf.toString("UTF-8");
            return JsonUtils.toObj(json, new TypeReference<List<CharacterHolder>>() {
            });
        } catch (Exception e) {
            log.info("缓存 character.json 失败？？？？？？？？？？");
            return new ArrayList<>();
        }
    }

    private List<CharacterHolder> generateAllCharactersFromWeb() {
        List<CharacterHolder> result = new LinkedList<>();
        int index = 1;
        for (String type : Constant.CHARACTER_TYPE) {
//            status.setInTotalProgress(index++);
            String url = Constant.WIKI_PATH + type;
            result.addAll(processTypePage(url));
        }
        return result;
    }

    private List<CharacterHolder> processTypePage(String url) {
        Document document;
        try {
            log.info("访问英雄类型页面：{}", url);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.info("访问英雄类型页面 失败？？？？？？？？？？");
            throw new RuntimeException(e);
        }

        List<CharacterHolder> characterHolders = new LinkedList<>();

        Elements elements = document.getElementsByClass("solo_hero_box");
        int index = 1;
        int size = elements.size();
//        status.setSubProgress(size);
        for (Element e : elements) {
//            status.setInSubProgress(index++);
            String heroName = e.select("div.name>span>b").text();
            String heroUrl = Constant.WIKI_BASE_PATH + e.select("div.img>a").attr("href");
            CharacterHolder characterHolder = processHeroPage(heroUrl, heroName);
            characterHolders.add(characterHolder);
        }

        return characterHolders;
    }

    private CharacterHolder processHeroPage(String url, String heroName) {
        Character.CharacterBuilder characterBuilder = Character.builder();

        Document document;
        try {
            log.info("访问具体英雄页面：{}", url);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.info("访问英雄类型页面 失败？？？？？？？？？？processHeroPage");
            throw new RuntimeException(e);
        }

        String heroImgUrl = document.select("div.cqhero_img>img").attr("src");
        characterBuilder.imageUrl(defaultImgUrl(heroImgUrl));

        String title = document.title();
        characterBuilder.title(title);

        characterBuilder.name(heroName);

        characterBuilder.pageUrl(url);

        characterBuilder.content(
                processContent(
                        document.select("div.hero_story").text(),
                        document.select("div.quote-box").text()
                )
        );

        Character character = characterBuilder.build();
        CharacterHolder characterHolder = new CharacterHolder();
        characterHolder.setCharacter(character);
        characterHolder.addNickName(heroName);
        for (Element element : document.select("div.cqframe_oth-name>p>span")) {
            characterHolder.addNickName(element.text());
        }

        return characterHolder;
    }

    private String defaultImgUrl(String url) {
        return StringUtils.isEmpty(url) ? Constant.WIKI_LOGO_PATH : url;
    }

    private String processContent(String story, String quote) {
        String result = story;
        if (StringUtils.isEmpty(result)) {
            result = quote;
        }
        if (result.length() > Constant.THIRTY) {
            result = result.substring(0, Constant.THIRTY) + Constant.ETC;
        }
        return result;
    }
}
