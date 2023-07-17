package org.example.core.util;

import lombok.experimental.UtilityClass;
import org.example.core.entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@UtilityClass
public class DataConverter {

    public static List<TargetStructure> convert(Root input) {
        List<TargetStructure> result = new ArrayList<>();
        for (ChatSession chatSession : input.getChatSessions()) {
            var targetStructureBuilder = TargetStructure.builder()
                    .chatIdentifier(chatSession.getChatIdentifier());
            for (Message message : chatSession.getMessages()) {
                targetStructureBuilder.belongNumber(message.getBelongNumber());
                targetStructureBuilder.sendDate(message.getSendDate());
                targetStructureBuilder.text(message.getText());
                String memberLast = chatSession.getMembers().stream()
                        .filter(member -> member.getHandleId() == member.getHandleId())
                        .map(Member::getLast)
                        .findFirst()
                        .orElse("");
                targetStructureBuilder.memberLast(memberLast);

                result.add(targetStructureBuilder.build());
            }
        }
        result.sort(Comparator.comparing(TargetStructure::getSendDate));
        result.sort(Comparator.comparing(TargetStructure::getBelongNumber));
        return result;
    }
}
