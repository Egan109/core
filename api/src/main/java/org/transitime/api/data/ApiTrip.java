/*
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Transitime.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */

package org.transitime.api.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitime.ipc.data.IpcScheduleTimes;
import org.transitime.ipc.data.IpcTrip;

/**
 *
 *
 * @author SkiBu Smith
 *
 */
@XmlRootElement(name="trip")
public class ApiTrip {

    @XmlAttribute
    private int configRev;
    
    @XmlAttribute
    private String id;

    @XmlAttribute
    private Integer startTime;

    @XmlAttribute
    private Integer endTime;

    @XmlAttribute
    private String directionId;

    @XmlAttribute
    private String routeId;

    @XmlAttribute
    private String routeShortName;

    @XmlElement
    private ApiTripPattern tripPattern;

    @XmlAttribute
    private String serviceId;

    @XmlAttribute
    private String headsign;

    @XmlAttribute
    private String blockId;

    @XmlAttribute
    private String shapeId;

    @XmlElement(name="scheduleTime")
    private List<ApiScheduleTimes> scheduleTimes;

    /********************** Member Functions **************************/

    protected ApiTrip() {}
    
    /**
     * 
     * @param ipcTrip
     * @param includeStopPaths
     *            Stop paths are only included in output if this param set to
     *            true.
     */
    public ApiTrip(IpcTrip ipcTrip, boolean includeStopPaths) {
	configRev = ipcTrip.getConfigRev();
	id = ipcTrip.getId();
	startTime = ipcTrip.getStartTime();
	endTime = ipcTrip.getEndTime();
	directionId = ipcTrip.getDirectionId();
	routeId = ipcTrip.getRouteId();
	routeShortName = ipcTrip.getRouteShortName();
	tripPattern = 
		new ApiTripPattern(ipcTrip.getTripPattern(), includeStopPaths);
	serviceId = ipcTrip.getServiceId();
	headsign = ipcTrip.getHeadsign();
	blockId = ipcTrip.getBlockId();
	shapeId = ipcTrip.getShapeId();
	
	scheduleTimes = new ArrayList<ApiScheduleTimes>();
	for (IpcScheduleTimes ipcScheduleTimes : ipcTrip.getScheduleTimes()) {
	    scheduleTimes.add(new ApiScheduleTimes(ipcScheduleTimes));
	}
    }
}
