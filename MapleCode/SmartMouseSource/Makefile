
LIB_MAPLE_HOME = ../../libmaple

include ../../libmaple/Makefile

$(BUILD_PATH)/main.o: main.cpp $(BUILD_PATH)/core.o
	$(SILENT_CXX) $(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/core.o: IO.cpp Infrareds.cpp Sensors.cpp Ultrasonics.cpp
	$(SILENT_CXX) $(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $< 