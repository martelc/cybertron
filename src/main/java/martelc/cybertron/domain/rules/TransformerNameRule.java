package martelc.cybertron.domain.rules;

import martelc.cybertron.domain.exceptions.OptimusPrimeMeetsPredakingException;
import martelc.cybertron.domain.transformers.Transformer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class TransformerNameRule implements Comparator<Transformer> {

    private static final Set<String> WINNING_NAMES = new HashSet<>(Arrays.asList("Optimus Prime", "Predaking"));

    @Override
    public int compare(Transformer firstTransformer, Transformer secondTransformer) {
        boolean isFirstOpponentAWinningName = WINNING_NAMES.contains(firstTransformer.getName());
        boolean isSecondOpponentAWinningName = WINNING_NAMES.contains(secondTransformer.getName());

        int compareResult = 0;
        if (isFirstOpponentAWinningName && !isSecondOpponentAWinningName) {
            compareResult = 1;
        } else if (!isFirstOpponentAWinningName && isSecondOpponentAWinningName) {
            compareResult = -1;
        } else if (isFirstOpponentAWinningName) {
            throw new OptimusPrimeMeetsPredakingException();
        }

        return compareResult;
    }
}
