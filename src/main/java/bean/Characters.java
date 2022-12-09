package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Characters {
    @Builder.Default
    private List<CharacterHolder> characterHolders = new LinkedList<>();

    public void addCharacter(CharacterHolder characterHolder) {
        characterHolders.add(characterHolder);
    }
}
