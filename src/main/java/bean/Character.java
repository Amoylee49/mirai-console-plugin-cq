package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Builder.Default
    public String name ="四天王夏洛特";
    private String title;
    private String content;
    private String imageUrl;
    private String pageUrl;


}
