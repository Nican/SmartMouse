
LIB_MAPLE_HOME = ../../libmaple
# 	PATH=$PATH:../arm/bin

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

$(BUILD_PATH)/DriveControl.o: DriveControl.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/MiceSensors.o: MiceSensors.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

$(BUILD_PATH)/main.o: main.cpp
	$(CXX) $(CFLAGS) $(CXXFLAGS) $(LIBMAPLE_INCLUDES) $(WIRISH_INCLUDES) -o $@ -c $<

#$(BUILD_PATH)/main.o: $(BUILD_PATH)/IO.o $(BUILD_PATH)/Infrareds.o $(BUILD_PATH)/Sensors.o $(BUILD_PATH)/Ultrasonics.o $(BUILD_PATH)/newmain.o $(BUILD_PATH)/DriveControl.o
#	arm-none-eabi-ld -r $(BUILD_PATH)/IO.o \
#		$(BUILD_PATH)/Infrareds.o $(BUILD_PATH)/Sensors.o \
#		$(BUILD_PATH)/Ultrasonics.o $(BUILD_PATH)/newmain.o \
#		$(BUILD_PATH)/DriveControl.o \
#		-o $(BUILD_PATH)/main.o 

$(BUILD_PATH)/$(BOARD).elf: $(BUILDDIRS) $(TGT_BIN)  $(BUILD_PATH)/IO.o $(BUILD_PATH)/Infrareds.o $(BUILD_PATH)/Sensors.o $(BUILD_PATH)/Ultrasonics.o $(BUILD_PATH)/main.o $(BUILD_PATH)/DriveControl.o $(BUILD_PATH)/MiceSensors.o
	$(CXX) $(LDFLAGS) -o $@ $(TGT_BIN) \
	$(BUILD_PATH)/IO.o $(BUILD_PATH)/Infrareds.o $(BUILD_PATH)/Sensors.o \
	$(BUILD_PATH)/Ultrasonics.o $(BUILD_PATH)/main.o \
	$(BUILD_PATH)/DriveControl.o $(BUILD_PATH)/MiceSensors.o \
	-Wl,-Map,$(BUILD_PATH)/$(BOARD).map