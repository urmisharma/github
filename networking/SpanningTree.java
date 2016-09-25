import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class SpanningTree {

	private MyFileReader myFileReader;
	private MyRandom myRandom;

	public SpanningTree(String path) {
		myFileReader = new MyFileReader(path);
	}
	
	public ArrayList<String> readFile() {
		ArrayList<String> myOldList = myFileReader.readFile();
		ArrayList<String> myNewList = new ArrayList<String>();

		for (int i = 0; i < myOldList.size(); i++) {
			String currentList = myOldList.get(i);
			String[] tokenCurrentList = currentList.split(" ");

			if (tokenCurrentList[1].equals("R")) {
				myRandom = new MyRandom(Integer.parseInt(tokenCurrentList[0]));
				myNewList.add(tokenCurrentList[0] + " " + myRandom.getRandomConnections(10));
			} else {
				myNewList.add(currentList);
			}
		}

		return myNewList;
	}

	public ArrayList<String> parseInputInToConnections(String input) 
	{
		ArrayList<String> parsedConnections = new ArrayList<String>();
		String[] tempParsedConnections = input.split(" ");

		for (int i = 1; i < tempParsedConnections.length; i++) {
			parsedConnections.add(tempParsedConnections[i]);
		}

		return parsedConnections;
	}

	public void run() {
		ArrayList<String> input = this.readFile();

		for (int i = 0; i < input.size(); i++) {
			System.out.println("Working on Input");
			System.out.println(input.get(i));
			System.out.println();

			SpanningTreeAlgorithm stAlgo = new SpanningTreeAlgorithm(this.parseInputInToConnections(input.get(i)));
			stAlgo.run();
		}
	}

	private class SpanningTreeAlgorithm {
		private ArrayList<String> input;
		private Graph graph;

		public SpanningTreeAlgorithm(ArrayList<String> input) {
			this.input = input;
			graph = new Graph();
			createGraph();
		}

		public void run() {
			printAllSwitch();
			printAllConnections();

			int i = 1;

			while (!graph.isFinalState()) {
				System.out.println(i + " send, received and updated");
				graph = sendReceiveUpdate(graph);
				printAllSwitch();
				i++;
			}

			printFinalConnections();
		}

		public Graph sendReceiveUpdate(Graph oldGraph) {

			Graph newGraph = new Graph();

			for (int i = 0; i < oldGraph.getAllSwitch().size(); i++) {
				Switch newSwitch = updateSwitch(oldGraph.getAllSwitch().get(i), oldGraph);
				newGraph.addSwitch(newSwitch);
			}

			newGraph.setAllConnections(oldGraph.getAllConnections());

			return newGraph;
		}

		private Switch updateSwitch(Switch oldSwitch, Graph oldGraph) 
		{

			Switch newSwitch;
			if (oldSwitch.getNextRoot().equals("1")) {
				newSwitch = oldSwitch;
			} else {
				newSwitch = new Switch(oldSwitch.getSwitchId());
				int nextRoot = Integer.parseInt(oldSwitch.getNextRoot());

				Switch switchWithLeastNextRoot = null;

				for (int i = 0; i < oldSwitch.getLinkedSwitches().size(); i++) {
					if (switchWithLeastNextRoot == null && Integer.parseInt(
							oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i)).getNextRoot()) < nextRoot) {
						switchWithLeastNextRoot = oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i));
					}

					else {
						if (switchWithLeastNextRoot != null
								&& Integer.parseInt(switchWithLeastNextRoot.getNextRoot()) > Integer.parseInt(
										oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i)).getNextRoot())) {
							switchWithLeastNextRoot = oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i));
						}

						else if (switchWithLeastNextRoot != null
								&& Integer.parseInt(switchWithLeastNextRoot.getNextRoot()) == Integer.parseInt(
										oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i)).getNextRoot())) {
							if (Integer.parseInt(switchWithLeastNextRoot.getSwitchId()) > Integer
									.parseInt(oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i)).getSwitchId())) {
								switchWithLeastNextRoot = oldGraph.getSwitch(oldSwitch.getLinkedSwitches().get(i));
							}

						}
					}
				}

				if (switchWithLeastNextRoot != null) {
					newSwitch.setHub(switchWithLeastNextRoot.getHub() + 1);
					newSwitch.setLinkedSwitches(oldSwitch.getLinkedSwitches());
					newSwitch.setNextRoot(switchWithLeastNextRoot.getNextRoot());
					newSwitch.setConnectionToNextRoot(switchWithLeastNextRoot.getSwitchId());
				}

				else {
					newSwitch = oldSwitch;
				}
			}

			return newSwitch;
		}

		public void printAllSwitch() {
			System.out.println("Printing All Switch Information");
			System.out.println("*************************************************");
			for (int i = 0; i < graph.getAllSwitch().size(); i++) {
				System.out.println("SwitchId = " + graph.getAllSwitch().get(i).getSwitchId() + "         "
						+ "NextRoot = " + graph.getAllSwitch().get(i).getNextRoot() + "         " + "Hub = "
						+ graph.getAllSwitch().get(i).getHub());

			}
			System.out.println("*************************************************");
			System.out.println("");
		}

		public void printAllConnections() {
			System.out.println("Printing All The Connections");
			System.out.println(
					"******************************************************************************************************************");
			for (int i = 0; i < graph.getAllConnections().size(); i++) {
				System.out.println("SourceSwitch = " + graph.getAllConnections().get(i).getSourceSwitch() + "         "
						+ "SourceSwitchPortId = " + graph.getAllConnections().get(i).getSourceSwitchPortId()
						+ "         " + "DestinationSwitch = " + graph.getAllConnections().get(i).getDestinationSwitch()
						+ "         " + "DestinationSwitchPortId = "
						+ graph.getAllConnections().get(i).getDestinationSwitchPortId());
			}
			System.out.println(
					"******************************************************************************************************************");
			System.out.println("");
		}

		public void printFinalConnections() {
			System.out.println("Printing Only Required Connections");
			System.out.println(
					"******************************************************************************************************************");
			for (int i = 0; i < graph.getAllSwitch().size(); i++) {
				if (!graph.getAllSwitch().get(i).getSwitchId().equals("1")) {

					Connection connection = graph.getLeastConnectionBetweenSwitch(graph.getAllSwitch().get(i),
							graph.getSwitch(graph.getAllSwitch().get(i).getConnectionToNextRoot()));
					System.out.println("SourceSwitch = " + connection.getSourceSwitch() + "         "
							+ "SourceSwitchPortId = " + connection.getSourceSwitchPortId() + "         "
							+ "DestinationSwitch = " + connection.getDestinationSwitch() + "         "
							+ "DestinationSwitchPortId = " + connection.getDestinationSwitchPortId());
				}
			}
			System.out.println(
					"******************************************************************************************************************");
			System.out.println("");
		}

		private void createGraph() {
			for (int i = 0; i < input.size(); i++) {
				String[] prasedInput = input.get(i).split("-");
				graph.addSwitch(new Switch(prasedInput[0]));
				graph.addSwitch(new Switch(prasedInput[1]));
				graph.addConnection(new Connection(prasedInput[0], prasedInput[1],
						graph.getSwitch(prasedInput[0]).getPortId(), graph.getSwitch(prasedInput[1]).getPortId()));
				graph.getSwitch(prasedInput[0]).addLinkedSwitches(prasedInput[1]);
				graph.getSwitch(prasedInput[1]).addLinkedSwitches(prasedInput[0]);
			}
		}
	}

	private class MyFileReader {
		private String path;

		public MyFileReader(String path) {
			this.path = path;
		}

		public ArrayList<String> readFile() {
			ArrayList<String> toRet = new ArrayList<String>();

			String currentLine = null;
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				while ((currentLine = bufferedReader.readLine()) != null) {
					toRet.add(currentLine);
				}
				bufferedReader.close();
			}

			catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + path + "'");
			} catch (IOException ex) {
				System.out.println("Error reading file '" + path + "'");
			}

			return toRet;
		}
	}

	private class MyRandom {
		Random random;
		int switchSize;
		HashSet<Integer> mySet;

		public MyRandom(int switchSize) {
			random = new Random();
			mySet = new HashSet<Integer>();
			this.switchSize = switchSize;
		}

		public String getRandomConnections(int size) {
			StringBuilder randomConnections = new StringBuilder();
			int connectionOne = -1;
			int connectionTwo = -1;

			for (int i = 0; i < size; i++) {
				connectionOne = random.nextInt(switchSize) + 1;
				mySet.add(connectionOne);
				connectionTwo = random.nextInt(switchSize) + 1;
				mySet.add(connectionTwo);

				while (connectionOne == connectionTwo) {
					connectionOne = random.nextInt(switchSize) + 1;
					mySet.add(connectionOne);
					connectionTwo = random.nextInt(switchSize) + 1;
					mySet.add(connectionTwo);
				}

				randomConnections.append(String.valueOf(connectionOne) + "-" + String.valueOf(connectionTwo) + " ");
			}

			for (int i = 1; i <= switchSize; i++) {
				if (!mySet.contains(i)) {
					randomConnections.append(getRandomConnection(i) + " ");
				}
			}
			return randomConnections.toString().trim();
		}

		public String getRandomConnection(int connectionOne) {
			int connectionTwo = random.nextInt(switchSize) + 1;

			while (connectionOne == connectionTwo) {
				connectionTwo = random.nextInt(switchSize) + 1;
			}

			return String.valueOf(connectionOne) + "-" + String.valueOf(connectionTwo);
		}
	}

	private class Switch {
		private String switchId;
		private String nextRoot;
		private int hub;
		private String connectionToNextRoot;
		private ArrayList<String> linkedSwitches;
		private int numOfPort;

		public Switch(String id) {
			switchId = id;
			nextRoot = id;
			hub = 0;
			linkedSwitches = new ArrayList<String>();
			connectionToNextRoot = "";
			numOfPort = 0;
		}

		public int getPortId() {
			numOfPort++;
			return numOfPort;
		}

		public String getConnectionToNextRoot() {
			return connectionToNextRoot;
		}

		public void setConnectionToNextRoot(String connectionToNextRoot) {
			this.connectionToNextRoot = connectionToNextRoot;
		}

		public void addLinkedSwitches(String newLink) {
			if (!linkedSwitches.contains(newLink)) {
				linkedSwitches.add(newLink);
			}
		}

		public String getSwitchId() {
			return switchId;
		}

		public void setSwitchId(String switchId) {
			this.switchId = switchId;
		}

		public String getNextRoot() {
			return nextRoot;
		}

		public void setNextRoot(String nextRoot) {
			this.nextRoot = nextRoot;
		}

		public int getHub() {
			return hub;
		}

		public void setHub(int hub) {
			this.hub = hub;
		}

		public ArrayList<String> getLinkedSwitches() {
			return linkedSwitches;
		}

		public void setLinkedSwitches(ArrayList<String> linkedSwitches) {
			this.linkedSwitches = linkedSwitches;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((switchId == null) ? 0 : switchId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Switch other = (Switch) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (switchId == null) {
				if (other.switchId != null)
					return false;
			} else if (!switchId.equals(other.switchId))
				return false;
			return true;
		}

		private SpanningTree getOuterType() {
			return SpanningTree.this;
		}
	}

	private class Connection {
		private String sourceSwitch;
		private String destinationSwitch;
		private int sourceSwitchPortId;
		private int destinationSwitchPortId;

		public Connection(String sourceSwitch, String destinationSwitch, int sourceSwitchPortId,
				int destinationSwitchPortId) {
			this.sourceSwitch = sourceSwitch;
			this.destinationSwitch = destinationSwitch;
			this.sourceSwitchPortId = sourceSwitchPortId;
			this.destinationSwitchPortId = destinationSwitchPortId;
		}

		public String getSourceSwitch() {
			return sourceSwitch;
		}

		public void setSourceSwitch(String sourceSwitch) {
			this.sourceSwitch = sourceSwitch;
		}

		public String getDestinationSwitch() {
			return destinationSwitch;
		}

		public void setDestinationSwitch(String destinationSwitch) {
			this.destinationSwitch = destinationSwitch;
		}

		public int getSourceSwitchPortId() {
			return sourceSwitchPortId;
		}

		public void setSourceSwitchPortId(int sourceSwitchPortId) {
			this.sourceSwitchPortId = sourceSwitchPortId;
		}

		public int getDestinationSwitchPortId() {
			return destinationSwitchPortId;
		}

		public void setDestinationSwitchPortId(int destinationSwitchPortId) {
			this.destinationSwitchPortId = destinationSwitchPortId;
		}
	}

	private class Graph {
		private ArrayList<Switch> allSwitch;
		private ArrayList<Connection> allConnections;

		public Graph() {
			allSwitch = new ArrayList<Switch>();
			allConnections = new ArrayList<Connection>();
		}

		public boolean isFinalState() {
			boolean isFinalState = true;

			int i = 0;
			while (isFinalState == true && i < allSwitch.size()) {

				if (!allSwitch.get(i).getNextRoot().equals("1")) {
					isFinalState = false;
				}
				i++;
			}

			return isFinalState;
		}

		public void setAllConnections(ArrayList<Connection> allConnections) {
			this.allConnections = allConnections;
		}

		public ArrayList<Connection> getAllConnections() {
			return allConnections;
		}

		public ArrayList<Switch> getAllSwitch() {
			return allSwitch;
		}

		public Switch getSwitch(String switchId) {
			Switch requiredSwitch = null;

			int i = 0;
			while (requiredSwitch == null && i < allSwitch.size()) {
				if (allSwitch.get(i).getSwitchId().equals(switchId)) {
					requiredSwitch = allSwitch.get(i);
				}
				i++;
			}

			return requiredSwitch;
		}

		public void setAllSwitch(ArrayList<Switch> allSwitch) {
			this.allSwitch = allSwitch;
		}

		public void addSwitch(Switch newSwitch) {
			if (!allSwitch.contains(newSwitch)) {
				allSwitch.add(newSwitch);
			}
		}

		public void addConnection(Connection newConnection) {
			allConnections.add(newConnection);
		}

		public Connection getLeastConnectionBetweenSwitch(Switch source, Switch destination) {
			Connection leastConnection = null;

			for (int i = 0; i < allConnections.size(); i++) {

				if ((allConnections.get(i).getSourceSwitch().equals(source.getSwitchId())
						&& allConnections.get(i).getDestinationSwitch().equals(destination.getSwitchId()))
						|| (allConnections.get(i).getSourceSwitch().equals(destination.getSwitchId())
								&& allConnections.get(i).getDestinationSwitch().equals(source.getSwitchId()))) {

					if (leastConnection == null) {

						leastConnection = allConnections.get(i);
					} else {
						if (allConnections.get(i).getSourceSwitchPortId() < leastConnection.getSourceSwitchPortId()) {
							leastConnection = allConnections.get(i);
						}
					}
				}
			}

			return leastConnection;
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
		}

		else {
			SpanningTree mySpanningTree = new SpanningTree(args[0]);
			mySpanningTree.run();
		}
	}

	private static void usage() {
		System.out.println("usage:");
		System.out.println("      $java SpanningTree PathToInputFile.Extension");
	}
}
