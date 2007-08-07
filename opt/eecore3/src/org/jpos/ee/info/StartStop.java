/*
 *  jPOS Extended Edition
 *  Copyright (C) 2004 Alejandro P. Revilla
 *  jPOS.org (http://jpos.org)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.jpos.ee.info;

import org.jpos.ee.SysLog;
import org.jpos.q2.QBeanSupport;

public class StartStop extends QBeanSupport {
    long start;
    public void startService() {
        start = System.currentTimeMillis();
        new SysLog().log (cfg.get ("source", getName()), 
            "SYSTEM", SysLog.INFO, "Start");
    }
    public void stopService() {
        long elapsed = System.currentTimeMillis() - start;
        new SysLog().log (cfg.get ("source", getName()), 
            "SYSTEM", SysLog.INFO, "Stop", 
            "time up: " + elapsed / 1000 + " secs."
        );
    }
}
