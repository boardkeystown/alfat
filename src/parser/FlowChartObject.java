package parser;

import java.util.ArrayList;

public class FlowChartObject {
    public String codeBlock;
    public String label = null;
    public ArrayList<FlowChartObject> connections = new ArrayList<>();
}
