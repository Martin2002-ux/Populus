/*
 * @(#)Matrix3D.java    1.3 97/07/28
 *
 * Copyright (c) 1995, 2015 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

/** A fairly conventional 3D matrix object that can transform sets of
    3D points and perform a variety of manipulations on the transform.

    This file has been modified from its original to implement model scaling for
    Populus 3D graphs.
 */
package edu.umn.ecology.populus.plot.threedgrapher;

class Matrix3D {
	float xx, xy, xz, xo;
	float yx, yy, yz, yo;
	float zx, zy, zz, zo;

	/** Scaling factors */
	double sx, sy, sz;

	/** Create a new unit matrix */
	Matrix3D () {
		xx = 1.0f;
		yy = 1.0f;
		zz = 1.0f;
		sx = 1.0f;
		sy = 1.0f;
		sz = 1.0f;
	}
	/** Scale by f in all dimensions */
	void scale(float f) {
		xx *= f;
		xy *= f;
		xz *= f;
		xo *= f;
		yx *= f;
		yy *= f;
		yz *= f;
		yo *= f;
		zx *= f;
		zy *= f;
		zz *= f;
		zo *= f;
	}
	/** Scale along each axis independently */
	void scale(float xf, float yf, float zf) {
		xx *= xf;
		xy *= xf;
		xz *= xf;
		xo *= xf;
		yx *= yf;
		yy *= yf;
		yz *= yf;
		yo *= yf;
		zx *= zf;
		zy *= zf;
		zz *= zf;
		zo *= zf;
	}

	/** Use this method to scale (extend or shrink) the "distance"
       each point is from the origin.*/
	void scaleGraph(double nsx, double nsy, double nsz){
		sx = nsx;
		sy = nsy;
		sz = nsz;
	}

	/** Translate the origin */
	void translate(float x, float y, float z) {
		xo += x;
		yo += y;
		zo += z;
	}
	/** rotate theta degrees about the y axis */
	void yrot(double theta) {
		theta *= (Math.PI / 180);
		double ct = Math.cos(theta);
		double st = Math.sin(theta);

		float Nxx = (float) (xx * ct + zx * st);
		float Nxy = (float) (xy * ct + zy * st);
		float Nxz = (float) (xz * ct + zz * st);

		float Nzx = (float) (zx * ct - xx * st);
		float Nzy = (float) (zy * ct - xy * st);
		float Nzz = (float) (zz * ct - xz * st);

		xx = Nxx;
		xy = Nxy;
		xz = Nxz;
		zx = Nzx;
		zy = Nzy;
		zz = Nzz;
	}
	/** rotate theta degrees about the x axis */
	void xrot(double theta) {
		theta *= (Math.PI / 180);
		double ct = Math.cos(theta);
		double st = Math.sin(theta);

		float Nyx = (float) (yx * ct + zx * st);
		float Nyy = (float) (yy * ct + zy * st);
		float Nyz = (float) (yz * ct + zz * st);
		float Nyo = (float) (yo * ct + zo * st);

		float Nzx = (float) (zx * ct - yx * st);
		float Nzy = (float) (zy * ct - yy * st);
		float Nzz = (float) (zz * ct - yz * st);
		float Nzo = (float) (zo * ct - yo * st);

		yo = Nyo;
		yx = Nyx;
		yy = Nyy;
		yz = Nyz;
		zo = Nzo;
		zx = Nzx;
		zy = Nzy;
		zz = Nzz;
	}
	/** rotate theta degrees about the z axis */
	void zrot(double theta) {
		theta *= (Math.PI / 180);
		double ct = Math.cos(theta);
		double st = Math.sin(theta);

		float Nyx = (float) (yx * ct + xx * st);
		float Nyy = (float) (yy * ct + xy * st);
		float Nyz = (float) (yz * ct + xz * st);
		float Nyo = (float) (yo * ct + xo * st);

		float Nxx = (float) (xx * ct - yx * st);
		float Nxy = (float) (xy * ct - yy * st);
		float Nxz = (float) (xz * ct - yz * st);
		float Nxo = (float) (xo * ct - yo * st);

		yo = Nyo;
		yx = Nyx;
		yy = Nyy;
		yz = Nyz;
		xo = Nxo;
		xx = Nxx;
		xy = Nxy;
		xz = Nxz;
	}
	/** Multiply this matrix by a second: M = M*R */
	void mult(Matrix3D rhs) {
		float lxx = xx * rhs.xx + yx * rhs.xy + zx * rhs.xz;
		float lxy = xy * rhs.xx + yy * rhs.xy + zy * rhs.xz;
		float lxz = xz * rhs.xx + yz * rhs.xy + zz * rhs.xz;
		float lxo = xo * rhs.xx + yo * rhs.xy + zo * rhs.xz + rhs.xo;

		float lyx = xx * rhs.yx + yx * rhs.yy + zx * rhs.yz;
		float lyy = xy * rhs.yx + yy * rhs.yy + zy * rhs.yz;
		float lyz = xz * rhs.yx + yz * rhs.yy + zz * rhs.yz;
		float lyo = xo * rhs.yx + yo * rhs.yy + zo * rhs.yz + rhs.yo;

		float lzx = xx * rhs.zx + yx * rhs.zy + zx * rhs.zz;
		float lzy = xy * rhs.zx + yy * rhs.zy + zy * rhs.zz;
		float lzz = xz * rhs.zx + yz * rhs.zy + zz * rhs.zz;
		float lzo = xo * rhs.zx + yo * rhs.zy + zo * rhs.zz + rhs.zo;

		xx = lxx;
		xy = lxy;
		xz = lxz;
		xo = lxo;

		yx = lyx;
		yy = lyy;
		yz = lyz;
		yo = lyo;

		zx = lzx;
		zy = lzy;
		zz = lzz;
		zo = lzo;
	}

	/** Reinitialize to the unit matrix */
	void unit() {
		xo = 0;
		xx = 1;
		xy = 0;
		xz = 0;
		yo = 0;
		yx = 0;
		yy = 1;
		yz = 0;
		zo = 0;
		zx = 0;
		zy = 0;
		zz = 1;
	}

	/** Sets the scaling back to 1.*/
	void unitScale(){
		sx = 1.0d;
		sy = 1.0d;
		sz = 1.0d;
	}
	/** Resets the origin*/
	void resetOrigin(){
		xo = 0;
		yo = 0;
		zo = 0;
	}
	/** Transform nvert points from v into tv.  v contains the input
       coordinates in floating point.  Three successive entries in
       the array constitute a point.  tv ends up holding the transformed
       points as integers; three successive entries per point */
	void transform(float v[], int tv[], int nvert) {
		float lxx = xx, lxy = xy, lxz = xz, lxo = xo;
		float lyx = yx, lyy = yy, lyz = yz, lyo = yo;
		float lzx = zx, lzy = zy, lzz = zz, lzo = zo;
		for (int i = nvert * 3; (i -= 3) >= 0;) {
			double x = v[i    ]*sx;
			double y = v[i + 1]*sy;
			double z = v[i + 2]*sz;
			tv[i    ] = (int) (x * lxx + y * lxy + z * lxz + lxo);
			tv[i + 1] = (int) (x * lyx + y * lyy + z * lyz + lyo);
			tv[i + 2] = (int) (x * lzx + y * lzy + z * lzz + lzo);
		}
	}

	void transform(Point3D[][] m){
		for(int i = 0; i < m.length; i++)
			for(int j = 0; j < m[0].length; j++)
				transform(m[i][j].v,m[i][j].tv,1);
	}

	@Override
	public String toString() {
		return ("[" + xo + "," + xx + "," + xy + "," + xz + ";"
				+ yo + "," + yx + "," + yy + "," + yz + ";"
				+ zo + "," + zx + "," + zy + "," + zz + "]");
	}
}
