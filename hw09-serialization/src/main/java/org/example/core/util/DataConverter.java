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
            for (Message message : chatSession.getMessages()) {
                var targetStructure = new TargetStructure();
                targetStructure.setChatIdentifier(chatSession.getChatIdentifier());
                targetStructure.setBelongNumber(message.getBelongNumber());
                targetStructure.setSendDate(message.getSendDate());
                targetStructure.setText(message.getText());
                String memberLast = chatSession.getMembers().stream()
                        .filter(member -> member.getHandleId() == member.getHandleId())
                        .map(Member::getLast)
                        .findFirst()
                        .orElse("");
                targetStructure.setMemberLast(memberLast);

                result.add(targetStructure);
            }
        }
        result.sort(Comparator.comparing(TargetStructure::getSendDate));
        result.sort(Comparator.comparing(TargetStructure::getBelongNumber));
        return result;
    }
}
