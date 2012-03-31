
LIB_MAPLE_HOME = ../libmaple

include ../libmaple/Makefile

$(BUILD_PATH)/main.o: main.cpp
	$(SILENT_CXX) $(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $< 