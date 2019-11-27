metadata {
	definition (name: "Simulated Activity Sensor 2.0", namespace: "tomhenrich", author: "tomhenrich") {
		capability "Actuator"
		capability "Sensor"
		capability "Motion Sensor"
		
        command "setActive"
        command "setInactive"
	}

	// simulator metadata
	simulator {
		status "active": "zone report :: type: 19 value: 0031"
		status "inactive": "zone report :: type: 19 value: 0030"
	}

	// UI tile definitions
    tiles(scale: 2) {
		multiAttributeTile(name:"status", type: "generic", width: 6, height: 4){
			tileAttribute("device.motion", key: "PRIMARY_CONTROL") {
				attributeState("active", label:'Active', icon:"st.motion.motion.active", backgroundColor:"#00A0DC")
				attributeState("inactive", label:'Inactive', icon:"st.motion.motion.inactive", backgroundColor:"#CCCCCC")
			}
		}
		standardTile("reset", "device.motion", width: 2, height: 2) {
			state "active", label: 'Turn Off', action: setInactive, icon: "st.Home.home30"
			state "inactive", label: 'Turn On', action: setActive, icon: "st.Home.home30"
		}
		main "status"
		details (["status","reset"])
	}
}

def setActive() {
    sendEvent(name: "motion", value: "active")
}
def setInactive(){
    sendEvent(name: "motion", value: "inactive")
}