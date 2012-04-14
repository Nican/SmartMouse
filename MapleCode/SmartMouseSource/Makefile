
LIB_MAPLE_HOME = ../../libmaple
PATH=$PATH:../arm/bin

include ../../libmaple/Makefile

#$(SILENT_CXX) 
$(BUILD_PATH)/IO.o: IO.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/Infrareds.o: Infrareds.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/Sensors.o: Sensors.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/Ultrasonics.o: Ultrasonics.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<


$(BUILD_PATH)/main.o: main.cpp $(BUILD_PATH)/IO.o $(BUILD_PATH)/Infrareds.o $(BUILD_PATH)/Sensors.o $(BUILD_PATH)/Ultrasonics.o
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<
