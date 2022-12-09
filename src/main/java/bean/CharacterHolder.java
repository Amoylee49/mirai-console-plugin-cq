package bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class CharacterHolder {
    private List<String> nickNames = new LinkedList<>();
    public Character character;

    public void addNickName(String nickName) {
        nickNames.add(nickName);
    }
}
