package rs.ac.bg.etf.pp1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MJParserTest {

    static {
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
    }

    public static void main(String[] args) throws Exception {

        Logger log = Logger.getLogger(MJParserTest.class);

        Reader br = null;
        try {
            File sourceCode = new File(args[0]);
            log.info("Compiling source file: " + sourceCode.getAbsolutePath());

            br = new BufferedReader(new FileReader(sourceCode));
            Yylex lexer = new Yylex(br);

            MJParser p = new MJParser(lexer);
            Symbol s = p.parse();  //pocetak parsiranja

            Program prog = (Program)(s.value);

            Tab.init(); // Universe scope
            Tab.currentScope().addToLocals(new Obj (Obj.Type, "bool", new Struct(Struct.Bool)));

            // ispis sintaksnog stabla
            log.info(prog.toString(""));
            log.info("===================================");

            // ispis prepoznatih programskih konstrukcija
            SemanticPass v = new SemanticPass();
            prog.traverseBottomUp(v);

            Tab.dump();

            if(v.passed()){
                log.info("Parsiranje uspesno zavrseno!");

                // Generisanje koda
                File outputFile;
                if (args.length == 1)
                {
                    Path path = Paths.get(args[0]);
                    String  sFile= path.getFileName().toString();
                    int dotPos = sFile.lastIndexOf('.');
                    String sFileName = (dotPos != -1) ? sFile.substring(0, dotPos) : sFile;
                    outputFile = new File("out/" + sFileName + ".obj");
                }
                else
                {
                    outputFile = new File(args[1]);
                }

                if (outputFile.exists())
                {
                    outputFile.delete();
                }

                CodeGenerator codeGenerator = new CodeGenerator();
                prog.traverseBottomUp(codeGenerator);
                Code.dataSize = v.nVars;
                Code.mainPc = codeGenerator.getMainPc();
                Code.write(new FileOutputStream(outputFile));
            }else{
                log.error("Parsiranje NIJE uspesno zavrseno!");
            }
        }
        finally {
            if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
        }

    }


}