package vv.spoon.processor;

import spoon.reflect.code.CtInvocation;
import spoon.reflect.cu.CompilationUnit;
import spoon.reflect.cu.SourceCodeFragment;
import spoon.reflect.cu.SourcePosition;

/**
 * Created by romain on 28/03/17.
 */
public class CountProcessor extends LogProcessor {

    @Override
    public void process(CtInvocation element) {
        SourcePosition sp = element.getPosition();
        CompilationUnit compileUnit = sp.getCompilationUnit();

        //add /** before the invocation
        SourceCodeFragment before = new SourceCodeFragment(compileUnit.beginOfLineIndex(sp.getSourceStart()), "/**", 0);
        compileUnit.addSourceCodeFragment(before);

        //add **/ vv.spoon.logger.LogWriter.out( argument, newline, error); after the invocation
        Object argument = element.getArguments().get(0);
        String snippet = "**/\n\t\tvv.spoon.logger.CountMethodCall.increment(" + argument
                + ");\n";

        SourceCodeFragment after = new SourceCodeFragment(compileUnit.nextLineIndex(sp.getSourceEnd()), snippet, 0);
        compileUnit.addSourceCodeFragment(after);
    }
}
